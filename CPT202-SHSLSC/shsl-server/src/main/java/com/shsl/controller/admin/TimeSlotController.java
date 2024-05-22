package com.shsl.controller.admin;

import com.shsl.entity.TimeSlots;
import com.shsl.service.TimeSlotService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.shsl.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeslot")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;


    // 根据场地ID获取时间段列表
    @GetMapping("/list")
    public ResponseEntity<List<TimeSlots>> getTimeSlotsByPlaceId(@RequestParam int placeId) {
        List<TimeSlots> timeSlots = timeSlotService.getTimeSlotsByPlaceId(placeId);
        if (!timeSlots.isEmpty()) {
            return ResponseEntity.ok(timeSlots);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据星期几获取时间段列表
    @RequestMapping("/getPlacesByDate")
    @ApiOperation(value = "Show Detailed Timeslots")
    public Result<List<TimeSlots>> showPlacesByDate(@RequestParam String day, @RequestParam Integer staId) {
        List<TimeSlots> timeSlots = timeSlotService.getTimeSlotsByDay(day,staId);
        return Result.success(timeSlots);
    }

}

