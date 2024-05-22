package com.shsl.mapper;

import com.shsl.entity.TimeSlots;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TimeSlotMapper {

    @Select("SELECT * FROM timeslots WHERE slot_id = #{slotId}")
    TimeSlots findById(int slotId);

    @Select("SELECT * FROM timeslots")
    List<TimeSlots> findAll();

    @Insert("INSERT INTO timeslots (place_id, start_time, end_time, is_booked) VALUES (#{placeId}, #{startTime}, #{endTime}, #{isBooked})")
    @Options(useGeneratedKeys = true, keyProperty = "slotId")
    void insert(TimeSlots timeslot);

    @Update("UPDATE timeslots SET is_booked = #{isBooked} WHERE slot_id = #{slotId}")
    void updateBookingStatus(@Param("slotId") int slotId, @Param("isBooked") boolean isBooked);

    @Delete("DELETE FROM timeslots WHERE slot_id = #{slotId}")
    void deleteById(int slotId);

    @Select("SELECT * FROM timeslots WHERE place_id = #{placeId}")
    List<TimeSlots> selectTimeSlotsByPlaceId(int placeId);

    @Select("SELECT * FROM timeslots WHERE week_day = #{day} AND sta ORDER BY start_time, place_id")
    List<TimeSlots> selectTimeSlotsByDay(String day, Integer staId);
}
