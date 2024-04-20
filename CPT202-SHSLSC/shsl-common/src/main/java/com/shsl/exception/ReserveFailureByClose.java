package com.shsl.exception;

/**
 * 预约场地关闭异常
 */
public class ReserveFailureByClose extends BaseException {
    public ReserveFailureByClose() {
    }

    public ReserveFailureByClose(String msg) {
        super(msg);
    }

}
