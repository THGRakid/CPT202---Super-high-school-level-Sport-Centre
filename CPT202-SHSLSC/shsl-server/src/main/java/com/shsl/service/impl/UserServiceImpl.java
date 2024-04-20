package com.shsl.service.impl;

import com.shsl.constant.MessageConstant;
import com.shsl.dto.UserLoginDTO;
import com.shsl.entity.User;
import com.shsl.exception.AccountNotFoundException;
import com.shsl.exception.PasswordErrorException;
import com.shsl.mapper.UserMapper;
import com.shsl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 员工登录
     *
     * @param userLoginDTO
     * @return
     */
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

}