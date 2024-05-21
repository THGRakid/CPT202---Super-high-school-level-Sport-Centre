package com.shsl.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "展示场馆的数据格式")
public class StadiumListVO implements Serializable {

    @ApiModelProperty("场馆名字")
    private String staName;

    @ApiModelProperty("场地数量")
    private Integer placeNumber;

    @ApiModelProperty("场馆信息")
    private String staInfo;

    @ApiModelProperty("场馆图片")
    private String staPicture;

    @ApiModelProperty("场地人数限制")
    private Integer limit;

    @ApiModelProperty("场馆价格")
    private Integer price;

    @ApiModelProperty("场馆位置")
    private String location;

}