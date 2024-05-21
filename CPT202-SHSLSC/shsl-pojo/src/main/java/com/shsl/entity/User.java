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

    //用户唯一标识
    private Integer userId;

    //微信用户唯一标识
    private String openid;

    //姓名
    private String userName;

    //电话
    private String phone;

    //电子邮件
    private String email;

    //Gender 0 female 1 male
    private String sex;

    //密码
    private String password;

    //头像
    private String avatar;

    //背景
    private String background;

    //注册时间
    private LocalDateTime createTime;
}
