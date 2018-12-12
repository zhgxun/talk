package com.github.zhgxun.talk.common.util;

import lombok.Data;

/**
 * 统一返回规范
 *
 * @param <E>
 */
@Data
public class ResponseUtil<E> {
    private Integer code;
    private String message = "";
    private E data;
}
