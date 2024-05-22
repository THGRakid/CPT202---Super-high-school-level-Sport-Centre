package com.shsl.entity;

import java.sql.Time;
import java.time.LocalDateTime;
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
public class TimeSlots implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer slotId;
    private Integer placeId;
    private String weekDay;
    private String startTime;
    private String endTime;
    private boolean isBooked;
}
