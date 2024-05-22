package com.shsl.dto;

import lombok.Data;

@Data
public class ReservationDTO {
    private Integer slotId;
    private Integer userId;
    private String reservationDate;

}
