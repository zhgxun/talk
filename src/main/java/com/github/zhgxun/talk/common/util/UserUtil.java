package com.github.zhgxun.talk.common.util;

import com.github.zhgxun.talk.entity.UserEntity;

/**
 * 用户工具
 */
public class UserUtil implements AutoCloseable {

    private static final ThreadLocal<UserEntity> current = new ThreadLocal<>();

    public UserUtil(UserEntity entity) {
        current.set(entity);
    }

    public static UserEntity getCurrentUser() {
        return current.get();
    }

    @Override
    public void close() throws Exception {
        current.remove();
    }
}
