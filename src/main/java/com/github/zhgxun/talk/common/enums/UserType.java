package com.github.zhgxun.talk.common.enums;

public enum UserType implements ExtEnumOrdinalTypeHandler.ValuedEnum {
    QQ(1), WEIBO(2), WEIXIN(3);

    private int index;

    UserType(int index) {
        this.index = index;
    }

    @Override
    public int getValue() {
        return this.index;
    }
}
