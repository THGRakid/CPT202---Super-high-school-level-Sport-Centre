package com.shsl.exception;

/**
 * 账号被锁定异常
 */
public class AccountAlreadyExistsException extends BaseException {

    public AccountAlreadyExistsException() {
    }

    public AccountAlreadyExistsException(String msg) {
        super(msg);
    }

}
