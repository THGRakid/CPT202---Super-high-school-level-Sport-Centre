package com.shsl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/*
 * C端用户登录
 */
@Data
public class WeChatLoginDTO implements Serializable {

    private String code;

}
