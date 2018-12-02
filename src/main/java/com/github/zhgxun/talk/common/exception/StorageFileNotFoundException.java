package com.github.zhgxun.talk.common.exception;

public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException() {

    }

    public StorageFileNotFoundException(String msg) {
        super(msg);
    }

    public StorageFileNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
