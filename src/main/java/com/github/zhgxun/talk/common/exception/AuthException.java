package com.github.zhgxun.talk.common.exception;

public class AuthException extends RuntimeException {

    public AuthException() {

    }

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }
}
