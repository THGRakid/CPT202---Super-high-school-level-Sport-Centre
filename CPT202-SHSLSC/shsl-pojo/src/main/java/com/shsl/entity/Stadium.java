package com.shsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stadium implements Serializable {
    private static final long serialVersionUID = 1L;

    //场馆唯一标识
    private Integer staId;

    //场馆名字
    private String staName;

    //场地数量
    private Integer placeNumber;

    //开启时间
    private String openTime;

    //关闭时间
    private String closeTime;

    //场馆信息
    private String staInfo;

    //场馆图片
    private String staPicture;

    //场地人数限制
    private Integer limit;

    //场馆价格
    private Integer price;

    //场馆位置
    private String location;

    //场馆图片
    private String video;
}
