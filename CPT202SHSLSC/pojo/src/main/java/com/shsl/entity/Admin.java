package com.shsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adminId;

    private String adminName;

    private String password;

    private String email;

    private Integer power;

    private Date expirationDate;

    private  String role; // 判断使用者类型（管理员和普通用户）
}
