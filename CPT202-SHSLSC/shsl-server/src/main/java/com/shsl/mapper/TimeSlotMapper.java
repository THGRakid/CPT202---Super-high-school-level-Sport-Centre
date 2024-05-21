package com.shsl.mapper;

import com.shsl.entity.TimeSlots;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TimeSlotMapper {

    @Insert("INSERT INTO timeslots (placeId, startTime, endTime, isBooked) " +
            "VALUES (#{placeId}, #{startTime}, #{endTime}, #{isBooked})")
    int insertTimeSlot(TimeSlots timeSlot);

    @Select("SELECT * FROM timeslots WHERE placeId = #{placeId}")
    List<TimeSlots> selectTimeSlotsByPlaceId(int placeId);

    // 根据需要添加其他操作时间段的方法
}
