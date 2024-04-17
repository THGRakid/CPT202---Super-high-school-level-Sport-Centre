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
public class ReservationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    //预订记录唯一标识
    private Integer resId;

    //场地唯一标识
    private Integer placeId;

    //用户唯一标识
    private Integer userId;

    //到达时间
    private Date startTime;

    //结束时间
    private Date leaveTime;

    //人数
    private Integer people;
}
