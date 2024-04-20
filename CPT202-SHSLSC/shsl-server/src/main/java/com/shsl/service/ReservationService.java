package com.shsl.service;

import com.shsl.entity.ReservationRecord;

import java.util.List;

public interface ReservationService {

    /**
     * 预约场地
     * @param reservation 预约信息
     * @return 预约成功返回 true，否则返回 false
     */
    boolean makeReservation(ReservationRecord reservation);

    /**
     * 取消预约
     * @param reservationId 预约ID
     * @return 取消成功返回 true，否则返回 false
     */
    boolean cancelReservation(int reservationId);

    /**
     * 根据用户ID查询预约记录
     * @param userId 用户ID
     * @return 用户的预约记录列表
     */
    List<ReservationRecord> getReservationsByUserId(int userId);

    /**
     * 根据场地ID查询预约记录
     * @param stadiumId 场地ID
     * @return 场地的预约记录列表
     */
    List<ReservationRecord> getReservationsByStadiumId(int stadiumId);

    /**
     * 根据预约ID查询预约信息
     * @param reservationId 预约ID
     * @return 预约信息
     */
    ReservationRecord getReservationById(int reservationId);

}
