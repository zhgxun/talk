package com.github.zhgxun.talk.common.exception;

public class StorageException extends RuntimeException {

    public StorageException() {

    }

    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String msg, Throwable t) {
        super(msg, t);
    }
}
