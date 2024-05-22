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
    public List<TimeSlots> getTimeSlotsByPlaceId(int placeId) {
        return timeSlotMapper.selectTimeSlotsByPlaceId(placeId);
    }

    @Override
    public List<TimeSlots> getTimeSlotsByDay(String day, Integer staId) {
        return timeSlotMapper.selectTimeSlotsByDay(day, staId);
    }
}
