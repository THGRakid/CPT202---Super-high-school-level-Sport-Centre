package com.shsl.mapper;

import com.shsl.entity.ReservationRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {

    @Insert("INSERT INTO reservation_record (user_id, sta_id, booking_date) " +
            "VALUES (#{userId}, #{stadiumId}, #{reservationTime})")
    int insertReservation(ReservationRecord reservation);


    @Delete("DELETE FROM reservation_record WHERE res_id = #{reservationId}")
    int deleteReservationById(int reservationId);

    @Select("SELECT * FROM reservation_record WHERE user_id = #{userId}")
    List<ReservationRecord> selectReservationsByUserId(int userId);

    @Select("SELECT * FROM reservation_record WHERE sta_id = #{stadiumId}")
    List<ReservationRecord> selectReservationsByStadiumId(int stadiumId);

    @Select("SELECT * FROM reservation_record WHERE res_id = #{reservationId}")
    ReservationRecord selectReservationById(int reservationId);

    @Select("SELECT COUNT(*) FROM reservation_record WHERE sta_id = #{stadiumId}")
    int countReservationsByStadiumId(@Param("stadiumId") int stadiumId);
}
