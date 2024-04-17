package com.shsl.controller.admin;

import com.shsl.dto.AdminLoginDTO;
import com.shsl.entity.Admin;
import com.shsl.service.AdminService;
import com.shsl.utils.JwtUtil;
import com.shsl.vo.AdminLoginVO;
import com.shsl.constant.JwtClaimsConstant;
import com.shsl.properties.JwtProperties;
import com.shsl.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param adminLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        log.info("员工登录：{}", adminLoginDTO);

        Admin admin = adminService.login(adminLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, admin.getAdminId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        AdminLoginVO employeeLoginVO = AdminLoginVO.builder()
                .adminId(admin.getAdminId())
                .adminName(admin.getAdminName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}
