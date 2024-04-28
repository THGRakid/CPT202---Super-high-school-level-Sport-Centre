package com.shsl.service;

import com.shsl.dto.UserLoginDTO;
import com.shsl.dto.WeChatLoginDTO;
import com.shsl.entity.User;

public interface UserService {
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 微信登录
     * @param weChatLoginDTO
     * @return
     */
    User wxLogin(WeChatLoginDTO weChatLoginDTO);
}
