package com.shsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    //管理员唯一标识
    private Integer adminId;

    //管理员姓名
    private String adminName;

    //部门姓名
    private String depName;

    //电话
    private String phone;

    //密码
    private String password;

    //登录邮箱
    private String email;

    //权利等级：1~3
    private Integer power;

    //创建时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //权限到期时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationTime;
}