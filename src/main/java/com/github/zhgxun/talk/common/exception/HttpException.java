package com.github.zhgxun.talk.common.exception;

public class HttpException extends RuntimeException {

    public HttpException() {

    }

    public HttpException(String msg) {
        super(msg);
    }

    public HttpException(String msg, Throwable t) {
        super(msg, t);
    }
}
