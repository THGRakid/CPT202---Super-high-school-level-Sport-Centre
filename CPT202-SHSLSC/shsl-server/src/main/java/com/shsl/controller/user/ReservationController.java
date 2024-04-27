package com.shsl.controller.user;

import com.shsl.entity.ReservationRecord;
import com.shsl.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 提交预约请求
    @PostMapping("/make")
    public ResponseEntity<String> makeReservation(@RequestBody ReservationRecord reservation) {
        boolean success = reservationService.makeReservation(reservation);
        if (success) {
            return ResponseEntity.ok("预约成功！");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("预约失败，请重试！");
        }
    }
    // 取消预约
    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable int reservationId) {
        boolean success = reservationService.cancelReservation(reservationId);
        if (success) {
            return ResponseEntity.ok("预约已取消！");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无法找到预约记录，取消失败！");
        }
    }

    // 获取预约记录
    @GetMapping("/records")
    public ResponseEntity<?> getReservations(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer stadiumId,
            @RequestParam(required = false) Integer reservationId) {

        if (userId != null) {
            return getReservationsByUserId(userId);
        } else if (stadiumId != null) {
            return getReservationsByStadiumId(stadiumId);
        } else if (reservationId != null) {
            ReservationRecord record = getReservationById(reservationId);
            if (record != null) {
                return ResponseEntity.ok(Collections.singletonList(record));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("未提供有效的查询参数");
        }
    }
    // 根据用户ID获取预约记录
    private ResponseEntity<List<ReservationRecord>> getReservationsByUserId(int userId) {
        List<ReservationRecord> reservations = reservationService.getReservationsByUserId(userId);
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据场地ID获取预约记录
    private ResponseEntity<List<ReservationRecord>> getReservationsByStadiumId(int stadiumId) {
        List<ReservationRecord> reservations = reservationService.getReservationsByStadiumId(stadiumId);
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据预约ID获取预约记录
    private ReservationRecord getReservationById(int reservationId) {
        return reservationService.getReservationById(reservationId);
    }
}
