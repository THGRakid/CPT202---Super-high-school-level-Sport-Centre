package com.shsl.controller.admin;

import com.shsl.entity.TimeSlots;
import com.shsl.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeslot")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    // 添加时间段
    @PostMapping("/add")
    public ResponseEntity<String> addTimeSlot(@RequestBody TimeSlots timeSlot) {
        boolean success = timeSlotService.addTimeSlot(timeSlot);
        if (success) {
            return ResponseEntity.ok("时间段添加成功！");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("时间段添加失败，请重试！");
        }
    }

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

    // 根据需要添加其他操作时间段的方法
}
