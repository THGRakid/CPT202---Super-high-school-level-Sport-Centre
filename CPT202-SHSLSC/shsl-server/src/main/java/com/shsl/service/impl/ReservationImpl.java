package com.shsl.service.impl;

import com.shsl.dto.ReservationDTO;
import com.shsl.entity.ReservationRecord;
import com.shsl.entity.TimeSlots;

import com.shsl.mapper.ReservationMapper;
import com.shsl.mapper.TimeSlotMapper;
import com.shsl.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private TimeSlotMapper timeSlotMapper;

    @Override
    public boolean makeReservation(ReservationDTO reservationDTO) {
        Integer slotId = reservationDTO.getSlotId();
        LocalDate reservationDate = LocalDate.parse(reservationDTO.getReservationDate());

        boolean isReserved = reservationMapper.existsByTimeslotAndReservationDate(slotId, reservationDate);
        if (isReserved) {
            return false; // Timeslot already reserved
        }

        TimeSlots timeslot = timeSlotMapper.findById(slotId);
        if (timeslot == null || timeslot.isBooked()) {
            return false; // Timeslot is already booked or does not exist
        }

        timeslot.setBooked(true);
        timeSlotMapper.updateBookingStatus(slotId, true);

        ReservationRecord reservation = new ReservationRecord();
        reservation.setTimeSlot(timeslot); // 设置 TimeSlot
        reservation.setUserId(reservationDTO.getUserId());
        reservation.setBookingDate(reservationDate); // 确保这个方法存在

        int rowsAffected = reservationMapper.insert(reservation);
        return rowsAffected > 0;
    }

    @Override
    public boolean cancelReservation(int reservationId) {
        ReservationRecord reservation = reservationMapper.findById(reservationId);
        if (reservation == null) {
            return false; // Reservation does not exist
        }

        TimeSlots timeslot = timeSlotMapper.findById(reservation.getTimeSlot().getSlotId());
        if (timeslot != null) {
            timeslot.setBooked(false);
            timeSlotMapper.updateBookingStatus(timeslot.getSlotId(), false);
        }

        int rowsAffected = reservationMapper.deleteReservationById(reservationId);
        return rowsAffected > 0;
    }

    @Override
    public List<ReservationRecord> getReservationsByUserId(int userId) {
        return reservationMapper.selectReservationsByUserId(userId);
    }

    @Override
    public List<ReservationRecord> getReservationsByStadiumId(int stadiumId) {
        return reservationMapper.selectReservationsByStadiumId(stadiumId);
    }

    @Override
    public ReservationRecord getReservationById(int reservationId) {
        return reservationMapper.findById(reservationId);
    }

    @Override
    public List<ReservationRecord> getAll() {
        return reservationMapper.findAll();
    }
}
