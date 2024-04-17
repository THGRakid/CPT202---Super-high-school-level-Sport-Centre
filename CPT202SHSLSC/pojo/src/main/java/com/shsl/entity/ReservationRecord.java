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

    private Integer resId;

    private Integer placeId;

    private Integer userId;

    private String userName;

    private Date bookingDate;

    private Date startTime;

    private Date leaveTime;

    private String BookingStatus; // To be confirmed, confirmed, cancelled, etc

    private Integer people;
}
