package com.github.zhgxun.talk.common.util;

import com.github.zhgxun.talk.config.Code;
import lombok.Data;

/**
 * 统一返回规范
 *
 * @param <T>
 */
@Data
public class ResponseUtil<T> {
    private Integer code;
    private String message;
    private T data;

    private ResponseUtil() {
        this.code = Code.SUCCESS;
        this.message = "";
        this.data = null;
    }

    public ResponseUtil(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseUtil(T t) {
        this();
        this.data = t;
    }
}
