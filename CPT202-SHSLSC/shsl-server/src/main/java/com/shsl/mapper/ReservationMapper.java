package com.shsl.mapper;

import com.shsl.entity.ReservationRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {

    @Insert("INSERT INTO reservation (userId, staId, bookingDate) " +
            "VALUES (#{userId}, #{stadiumId}, #{reservationTime})")
    int insertReservation(ReservationRecord reservation);


    @Delete("DELETE FROM reservation WHERE resId = #{reservationId}")
    int deleteReservationById(int reservationId);

    @Select("SELECT * FROM reservation WHERE userId = #{userId}")
    List<ReservationRecord> selectReservationsByUserId(int userId);

    @Select("SELECT * FROM reservation WHERE staId = #{stadiumId}")
    List<ReservationRecord> selectReservationsByStadiumId(int stadiumId);

    @Select("SELECT * FROM reservation WHERE resId = #{reservationId}")
    ReservationRecord selectReservationById(int reservationId);
}
