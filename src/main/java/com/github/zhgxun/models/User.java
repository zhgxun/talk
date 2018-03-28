package com.github.zhgxun.models;

/**
 * 用户对象
 */
public class User {

    private long id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
