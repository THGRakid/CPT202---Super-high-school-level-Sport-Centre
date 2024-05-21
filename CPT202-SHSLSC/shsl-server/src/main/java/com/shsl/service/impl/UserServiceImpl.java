package com.shsl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shsl.constant.MessageConstant;
import com.shsl.constant.PasswordConstant;
import com.shsl.constant.PowerConstant;
import com.shsl.dto.*;
import com.shsl.entity.User;
import com.shsl.entity.User;
import com.shsl.exception.AccountAlreadyExistsException;
import com.shsl.exception.AccountNotFoundException;
import com.shsl.exception.LoginFailedException;
import com.shsl.exception.PasswordErrorException;
import com.shsl.mapper.UserMapper;
import com.shsl.properties.WeChatProperties;
import com.shsl.result.PageBean;
import com.shsl.result.PageResult;
import com.shsl.service.UserService;
import com.shsl.utils.HttpClientUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;


    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public User login(UserLoginDTO UserLoginDTO) {
        String userName = UserLoginDTO.getUserName();
        String password = UserLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(userName);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //Password comparison
        // Encrypt the plaintext password sent from the front-end
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }


        //3、返回实体对象
        return user;
    }


    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public User wxLogin(WeChatLoginDTO weChatLoginDTO) {
        String openid = getOpenid(weChatLoginDTO.getCode());

        //判断openid是否为空，如果为空表示登录失败，抛出业务异常
        if(openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //判断当前用户是否为新用户
        User user = userMapper.getByOpenid(openid);

        //如果是新用户，自动完成注册
        if(user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        //返回这个用户对象
        return user;
    }

    /**
     * 调用微信接口服务，获取微信用户的openid
     * @param code
     * @return
     */
    private String getOpenid(String code){
        //调用微信接口服务，获得当前微信用户的openid
        Map<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }

    // ------------------------------------------------------------------

    /**
     *登录后其他用户操作
     */

    @Override
    public List<User> selectAll() {
        List<User> users = userMapper.selectAll();

        return users;
    }

    @Override
    public void updateUserPhone(Integer id, String phone){
        userMapper.updateUserPhone(id, phone);
    }

    @Override
    public void updateUserEmail(Integer id, String email){
        userMapper.updateUserEmail(id, email);
    }

    @Override
    public void updateUserSex(Integer id, String sex){
        userMapper.updateUserSex(id, sex);
    }

    @Override
    public void updateUserAvatar(Integer id, String avatar){
        userMapper.updateUserAvatar(id, avatar);
    }

    /**
     * 新增用户
     *
     * @param userRegisterDTO
     */
    @Override
    public void insert(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        //对象属性拷贝
        BeanUtils.copyProperties(userRegisterDTO, user);

        if (userMapper.findUserNameCount(user.getUserName()) > 0 ) {
            //账号已存在
            throw new AccountAlreadyExistsException(MessageConstant.ACCOUNT_ALREADY_EXIST);
        }
        //设置密码，默认密码123456
        user.setPassword(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()));

        //设置当前记录的创建时间和修改时间
        user.setCreateTime(LocalDateTime.now());

        userMapper.insert(user);
    }

    /**
     * 分页查询
     *
     * @param userPageQueryDTO
     * @return
     */
    @Override
    public PageResult selectByPage(UserPageQueryDTO userPageQueryDTO) {
        // select * from user limit 0,10
        //开始分页查询
        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getPageSize());

        Page<User> page = userMapper.selectByPage(userPageQueryDTO);

        long total = page.getTotal();
        List<User> records = page.getResult();

        return new PageResult(total, records);
    }
    
    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
    
    /**
     * 编辑用户信息
     *
     * @param userDTO
     */
    @Override
    public void update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userMapper.update(user);
    }
    @Override
    public void deleteUserById(UserDTO userDTO) {
        userMapper.deleteUserById(userDTO.getUserId());
    }

    @Override
    public void deleteUserByIds(int[] ids) {
        userMapper.deleteUserByIds(ids);
    }


}