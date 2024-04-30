package com.shsl.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {

    private Integer userId;

    private String userName;

    private String phone;

    private String email;

    private String sex;

    private String avatar;

    private LocalDateTime createTime;

}