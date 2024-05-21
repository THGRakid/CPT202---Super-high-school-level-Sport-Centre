package com.shsl.service;

import com.shsl.entity.TimeSlots;

import java.util.List;

public interface TimeSlotService {

    /**
     * 添加时间段
     * @param timeSlot 时间段信息
     * @return 添加是否成功
     */
    boolean addTimeSlot(TimeSlots timeSlot);

    /**
     * 根据场地ID获取时间段列表
     * @param placeId 场地ID
     * @return 时间段列表
     */
    List<TimeSlots> getTimeSlotsByPlaceId(int placeId);

    // 根据需要添加其他操作时间段的方法
}
