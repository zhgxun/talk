package com.github.zhgxun.talk.common.enums;

public enum UserRole {
    ADMIN(1), NONE(2);

    private Integer index;

    UserRole(Integer index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
