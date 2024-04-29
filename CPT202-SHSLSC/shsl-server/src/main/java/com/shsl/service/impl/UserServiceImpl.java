package com.shsl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shsl.constant.MessageConstant;
import com.shsl.dto.UserLoginDTO;
import com.shsl.dto.WeChatLoginDTO;
import com.shsl.entity.User;
import com.shsl.exception.AccountNotFoundException;
import com.shsl.exception.LoginFailedException;
import com.shsl.exception.PasswordErrorException;
import com.shsl.mapper.UserMapper;
import com.shsl.properties.WeChatProperties;
import com.shsl.result.PageBean;
import com.shsl.service.UserService;
import com.shsl.utils.HttpClientUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
     * 员工登录
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public User login(UserLoginDTO UserLoginDTO) {
        String userName = UserLoginDTO.getUserName();
        String password = UserLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUserName(userName);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端传过来的明文密码进行加密处理
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
            userMapper.add(user);
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
    public void add(User user) {
        userMapper.add(user);
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

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public PageBean<User> selectByPage(int currentPage, int pageSize) {
        //1、计算开始索引及查询条目数
        /*
        参数1：开始索引 = (当前页码 - 1） * 每页显示条数
        参数2：查询条目数 = 每页显示条数
         */
        int begin = (currentPage-1)*pageSize;
        int size = pageSize;

        //2、查询当前页数据
        List<User> rowsInPage = userMapper.selectByPage(begin,size);

        //3、查询总记录数
        int totalCount = userMapper.selectTotalCount();

        //4、封装PageBean对象
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRowsInPage(rowsInPage);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    @Override
    public void deleteByIds(int[] ids) {
        userMapper.deleteByIds(ids);
    }

    @Override
    public PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user) {
        //1. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理user条件，模糊表达式
        String userName = user.getUserName();
        if (userName != null && userName.length() > 0) {
            user.setUserName("%" + userName + "%");
        }

        //2. 查询当前页数据
        List<User> rows = userMapper.selectByPageAndCondition(begin, size, user);

        //3. 查询总记录数
        int totalCount = userMapper.selectTotalCountByCondition(user);

        //4. 封装PageBean对象
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRowsInPage(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

}