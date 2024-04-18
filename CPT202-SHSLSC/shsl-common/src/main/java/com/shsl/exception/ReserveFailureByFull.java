package com.shsl.exception;

/**
 * 预约场地被占异常
 */
public class ReserveFailureByFull extends BaseException {
    public ReserveFailureByFull() {
    }

    public ReserveFailureByFull(String msg) {
        super(msg);
    }

}
