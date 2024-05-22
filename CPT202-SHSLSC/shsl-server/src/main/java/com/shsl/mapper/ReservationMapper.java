package com.shsl.mapper;

import com.shsl.entity.ReservationRecord;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

public interface ReservationMapper {

    @Select("SELECT * FROM reservation_record WHERE res_id = #{id}")
    @Results({
            @Result(property = "timeSlot", column = "slot_id",
                    one = @One(select = "com.shsl.mapper.TimeSlotsMapper.findById"))
    })
    ReservationRecord findById(int id);

    @Select("SELECT * FROM reservation_record")
    @Results({
            @Result(property = "timeSlot", column = "slot_id",
                    one = @One(select = "com.shsl.mapper.TimeSlotsMapper.findById"))
    })
    List<ReservationRecord> findAll();

    @Insert("INSERT INTO reservation_record(slot_id, user_id, booking_date) VALUES(#{slotId}, #{userId}, #{reservationDate})")
    void insert(Integer slotId, Integer userId, LocalDate reservationDate);

    @Delete("DELETE FROM reservation_record WHERE res_id = #{id}")
    int deleteReservationById(int id);

    @Select("SELECT COUNT(*) > 0 FROM reservation_record WHERE slot_id = #{slotId} AND booking_date = #{reservationDate}")
    boolean existsByTimeslotAndReservationDate(@Param("slotId") int slotId, @Param("reservationDate") LocalDate reservationDate);

    @Select("SELECT * FROM reservation_record WHERE user_id = #{userId}")
    @Results({
            @Result(property = "timeSlot", column = "slot_id",
                    one = @One(select = "com.shsl.mapper.TimeSlotsMapper.findById"))
    })
    List<ReservationRecord> selectReservationsByUserId(int userId);

    @Select("SELECT * FROM reservation_record WHERE slot_id IN (SELECT slot_id FROM timeslots WHERE place_id IN (SELECT place_id FROM place WHERE sta_id = #{stadiumId}))")
    @Results({
            @Result(property = "timeSlot", column = "slot_id",
                    one = @One(select = "com.shsl.mapper.TimeSlotsMapper.findById"))
    })
    List<ReservationRecord> selectReservationsByStadiumId(int stadiumId);
}
