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
     * 开放平台标识
     */
    private String openId;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
