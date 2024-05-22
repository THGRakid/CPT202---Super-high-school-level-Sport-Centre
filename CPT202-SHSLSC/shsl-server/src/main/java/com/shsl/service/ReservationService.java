package com.shsl.service;

import com.shsl.dto.ReservationDTO;
import com.shsl.entity.ReservationRecord;

import java.util.List;

public interface ReservationService {
    boolean makeReservation(ReservationDTO reservationDTO);

    boolean cancelReservation(int reservationId);

    List<ReservationRecord> getReservationsByUserId(int userId);

    List<ReservationRecord> getReservationsByStadiumId(int stadiumId);

    ReservationRecord getReservationById(int reservationId);

    List<ReservationRecord> getAll();
}
