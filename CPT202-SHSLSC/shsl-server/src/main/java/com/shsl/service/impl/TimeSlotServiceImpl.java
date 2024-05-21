package com.shsl.service.impl;

import com.shsl.entity.TimeSlots;
import com.shsl.mapper.TimeSlotMapper;
import com.shsl.service.TimeSlotService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotMapper timeSlotMapper;

    @Override
    public boolean addTimeSlot(TimeSlots timeSlot) {
        int rowsAffected = timeSlotMapper.insertTimeSlot(timeSlot);
        return rowsAffected > 0;
    }

    @Override
    public List<TimeSlots> getTimeSlotsByPlaceId(int placeId) {
        return timeSlotMapper.selectTimeSlotsByPlaceId(placeId);
    }

    // 根据需要添加其他操作时间段的方法
}
