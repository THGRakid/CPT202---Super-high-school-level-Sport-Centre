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

    //姓名
    private String userName;

    //电子邮件
    private String email;

    //Gender 0 female 1 male
    private String sex;

    //密码
    private String password;

    //头像
    private String avatar;

    //注册时间
    private LocalDateTime createTime;
}
