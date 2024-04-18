package com.shsl.exception;

/**
 * 禁止删除异常
 */
public class DeletionNotAllowedException extends BaseException {
    public DeletionNotAllowedException() {
    }

    public DeletionNotAllowedException(String msg) {
        super(msg);
    }

}
