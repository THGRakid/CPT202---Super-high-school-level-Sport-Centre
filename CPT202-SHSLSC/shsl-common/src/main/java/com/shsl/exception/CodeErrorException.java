package com.shsl.exception;


/**
 * 验证码错误异常
 */
public class CodeErrorException extends BaseException {
    public CodeErrorException() {
    }

    public CodeErrorException(String msg) {
        super(msg);
    }

}