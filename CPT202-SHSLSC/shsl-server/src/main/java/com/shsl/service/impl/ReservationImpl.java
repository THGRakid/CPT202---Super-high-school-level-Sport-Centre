package com.shsl.service.impl;

import com.shsl.entity.ReservationRecord;
import com.shsl.entity.Stadium;
import com.shsl.entity.User;
import com.shsl.mapper.ReservationMapper;
import com.shsl.service.ReservationService;
import com.shsl.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private StadiumService stadiumService;

    @Override
    public boolean makeReservation(ReservationRecord reservationRecord) {
        // 获取预约的场馆信息，包括最大容纳人数
        Stadium stadium = stadiumService.getStadiumById(reservationRecord.getStaId());

        // 查询当前场馆已预约的人数
        int currentReservationCount = reservationMapper.countReservationsByStadiumId(stadium.getStaId());

        // 检查当前预约人数是否已达到最大容纳人数
        if (currentReservationCount >= stadium.getLimit()) {
            return false; // 已达到上限，不允许添加新的预约记录
        }

        // 执行添加预约操作
        int rowsAffected = reservationMapper.insertReservation(reservationRecord);
        return rowsAffected > 0;
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


    @Override
    public List<ReservationRecord> getAll() {
        List<ReservationRecord> reservationRecords = reservationMapper.getAll();

        return reservationRecords;
    }
}
