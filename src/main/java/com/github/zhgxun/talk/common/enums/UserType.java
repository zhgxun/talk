package com.github.zhgxun.talk.common.enums;

public enum UserType {
    QQ(1), WEIBO(2), WEIXIN(3);

    private int index;

    UserType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
