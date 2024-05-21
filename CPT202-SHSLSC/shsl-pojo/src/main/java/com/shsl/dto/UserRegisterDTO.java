package com.shsl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/*
 * User Register
 */
@Data
@ApiModel(description = "The data model passed when the user registers")
public class UserRegisterDTO implements Serializable {

    @ApiModelProperty("user name")
    private String userName;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("code")
    private String code;

}
