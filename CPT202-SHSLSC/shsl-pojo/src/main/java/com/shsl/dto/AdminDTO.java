package com.shsl.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminDTO implements Serializable {

    private Integer adminId;

    private String adminName;

    private String email;

    private String power;

}