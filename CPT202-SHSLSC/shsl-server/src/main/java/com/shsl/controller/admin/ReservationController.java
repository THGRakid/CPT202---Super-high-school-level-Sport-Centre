package com.shsl.controller.admin;

import com.shsl.constant.MessageConstant;
import com.shsl.dto.ReservationDTO;
import com.shsl.entity.ReservationRecord;
import com.shsl.exception.AccountNotFoundException;
import com.shsl.exception.ReserveFailureByClose;
import com.shsl.exception.SessionFailtoRead;
import com.shsl.exception.TokenError;
import com.shsl.result.Result;
import com.shsl.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 提交预约请求
    @PostMapping("/make")
    public Result makeReservation(@RequestBody ReservationDTO reservationDTO, HttpSession session) {

        if (session != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId != null) {
                reservationDTO.setReservationDate(String.valueOf(LocalDate.now()));
                reservationDTO.setUserId(userId);

                boolean success = reservationService.makeReservation(reservationDTO);
                if (success) {
                    return Result.success();
                } else {
                    throw new ReserveFailureByClose(MessageConstant.RESERVE_FAILURE_BY_CLOSE);
                }
            } else {
                throw new TokenError(MessageConstant.TOKEN_ERROR);
            }
        } else {
            throw new SessionFailtoRead(MessageConstant.SESSION_FAIL_TO_READ);
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
