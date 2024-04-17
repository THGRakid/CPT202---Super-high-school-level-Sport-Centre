package com.shsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String email;

    //Gender 0 female 1 male
    private String sex;

    private String password;

    private LocalDateTime createTime;

    private  String phoneNumber;

    private  String role; // 判断使用者类型（管理员和普通用户）
}
