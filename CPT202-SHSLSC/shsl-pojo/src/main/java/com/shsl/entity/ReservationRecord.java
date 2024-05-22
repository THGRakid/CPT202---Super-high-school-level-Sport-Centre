package com.shsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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

    //场管唯一标识
    private Integer staId;

    //场地唯一标识
    private Integer placeId;

    //用户唯一标识
    private Integer userId;

    //用户名
    private String userName;

    //预定日期
    private LocalDate bookingDate;

    //到达时间
    private String startTime;

    //结束时间
    private String leaveTime;

    //To be confirmed, confirmed, cancelled, etc
    private String ReservationRecordStatus;

    //人数
    private Integer people;

    //价格
    private Integer amount;

    private TimeSlots timeSlot;

}