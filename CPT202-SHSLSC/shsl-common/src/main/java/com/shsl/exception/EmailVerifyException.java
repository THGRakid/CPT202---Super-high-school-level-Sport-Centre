package com.shsl.exception;

/**
 * 邮箱验证错误
 */
public class EmailVerifyException extends BaseException {
    public EmailVerifyException() {
    }

    public EmailVerifyException(String msg) {
        super(msg);
    }

}
