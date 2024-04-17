package com.shsl.service;

import com.shsl.dto.AdminLoginDTO;
import com.shsl.entity.Admin;

public interface AdminService {

    /**
     * 管理员登录
     * @param adminLoginDTO
     * @return
     */
    Admin login(AdminLoginDTO adminLoginDTO);

}