package com.shsl.service;

import com.shsl.entity.TimeSlots;

import java.util.List;

public interface TimeSlotService {

    /**
     * 根据场地ID获取时间段列表
     * @param placeId 场地ID
     * @return 时间段列表
     */
    List<TimeSlots> getTimeSlotsByPlaceId(int placeId);

    /**
     * 根据场地ID获取时间段列表
     * @param day 星期日
     * @return 时间段列表
     */
    List<TimeSlots> getTimeSlotsByDay(String day);
}
