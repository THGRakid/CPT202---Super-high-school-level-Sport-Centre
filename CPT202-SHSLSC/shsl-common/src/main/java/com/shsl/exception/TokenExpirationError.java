package com.shsl.exception;

/**
 * Token过期异常
 */
public class TokenExpirationError extends BaseException{

        public TokenExpirationError() {
        }

        public TokenExpirationError(String msg) {
            super(msg);
        }

}
