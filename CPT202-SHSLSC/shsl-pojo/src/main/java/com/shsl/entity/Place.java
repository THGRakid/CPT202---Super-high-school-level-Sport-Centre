package com.shsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    //场地唯一标识
    private Integer placeId;

    //属于哪个场馆
    private Integer staId;

    //status: not open / free / full
    private String status;
}
