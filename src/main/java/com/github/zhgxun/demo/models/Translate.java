package com.github.zhgxun.demo.models;

public class Translate {
    private final long id;
    private final String content;

    public Translate(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
