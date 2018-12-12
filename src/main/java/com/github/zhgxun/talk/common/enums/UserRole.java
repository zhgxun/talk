package com.github.zhgxun.talk.common.enums;

public enum UserRole implements ExtEnumOrdinalTypeHandler.ValuedEnum {
    ADMIN(1), NONE(2);

    private Integer index;

    UserRole(Integer index) {
        this.index = index;
    }

    @Override
    public int getValue() {
        return this.index;
    }
}
