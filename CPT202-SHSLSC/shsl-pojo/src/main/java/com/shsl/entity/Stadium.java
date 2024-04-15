package com.shsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stadium implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer staId;

    private String staName;

    private Integer placeNumber;

    private Date openTime;

    private Date closeTime;

    private String staInfo;

    private String staPicture;

    private Integer limit;

    private Integer price;

    private String location;
}
