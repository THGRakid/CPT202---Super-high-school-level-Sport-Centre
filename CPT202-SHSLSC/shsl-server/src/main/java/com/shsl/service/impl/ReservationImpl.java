package com.shsl.service.impl;

import com.shsl.entity.ReservationRecord;
import com.shsl.mapper.ReservationMapper;
import com.shsl.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public boolean makeReservation(ReservationRecord reservationRecord) {
        int rowsAffected = reservationMapper.insertReservation(reservationRecord);
        return rowsAffected > 0; // 如果插入操作成功，则返回 true，否则返回 false
    }

    @Override
    public boolean cancelReservation(int reservationId) {
        int rowsAffected = reservationMapper.deleteReservationById(reservationId);
        return rowsAffected > 0; // 如果删除操作成功，则返回 true，否则返回 false
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
        return reservationMapper.selectReservationById(reservationId);
    }
}
