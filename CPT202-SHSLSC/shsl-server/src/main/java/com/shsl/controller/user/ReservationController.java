package com.shsl.controller.user;

import com.shsl.entity.ReservationRecord;
import com.shsl.service.ReservationService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 提交预约请求
    @PostMapping("/make")
    public ResponseEntity<String> makeReservation(@RequestBody ReservationRecord reservation) {
        // 尝试进行预约
        boolean success = reservationService.makeReservation(reservation);
        // 返回适当的响应
        if (success) {
            return ResponseEntity.ok("预约成功！");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("预约失败，请重试！");
        }
    }

    // 取消预约
    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable int reservationId) {
        // 尝试取消预约
        boolean success = reservationService.cancelReservation(reservationId);
        // 返回适当的响应
        if (success) {
            return ResponseEntity.ok("预约已取消！");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无法找到预约记录，取消失败！");
        }
    }

    // 根据用户ID获取预约记录
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationRecord>> getReservationByUserId(@PathVariable int userId) {
        // 获取指定用户的预约记录
        List<ReservationRecord> reservations = reservationService.getReservationsByUserId(userId);
        // 返回适当的响应
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    // 根据场地ID获取预约记录
    @GetMapping("/stadium/{stadiumId}")
    public ResponseEntity<List<ReservationRecord>> getReservationByStadiumId(@PathVariable int stadiumId) {
        // 获取指定场地的预约记录
        List<ReservationRecord> reservations = reservationService.getReservationsByStadiumId(stadiumId);
        // 返回适当的响应
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/user/{userId}")
    public List<ReservationRecord> getReservationsByUserId(@PathVariable int userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<ReservationRecord> getReservationsByStadiumId(@PathVariable int stadiumId) {
        return reservationService.getReservationsByStadiumId(stadiumId);
    }

    @GetMapping("/{reservationId}")
    public ReservationRecord getReservationById(@PathVariable int reservationId) {
        return reservationService.getReservationById(reservationId);
    }
}
