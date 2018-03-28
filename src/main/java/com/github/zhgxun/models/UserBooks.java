package com.github.zhgxun.models;

/**
 * 用户图书关联表
 */
public class UserBooks {

    /**
     * 关联主键
     */
    private long id;

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 图书编号
     */
    private int number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
