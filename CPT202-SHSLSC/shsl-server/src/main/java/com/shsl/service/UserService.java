package com.shsl.service;

import com.shsl.dto.UserLoginDTO;
import com.shsl.entity.User;

public interface UserService {
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);
}
