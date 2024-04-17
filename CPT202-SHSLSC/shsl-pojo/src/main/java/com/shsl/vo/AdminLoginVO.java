package com.shsl.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "管理员登录返回的数据格式")
public class AdminLoginVO implements Serializable {

    @ApiModelProperty("主键值")
    private Integer adminId;

    @ApiModelProperty("用户名")
    private String adminName;

    @ApiModelProperty("jwt令牌")
    private String token;

}
