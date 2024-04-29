package com.shsl.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
public class AdminDTO implements Serializable {

    private Integer adminId;

    private String adminName;

    private String depName;

    private String phone;

    private String email;

    private LocalDateTime expirationTime;

    private Integer power;

}