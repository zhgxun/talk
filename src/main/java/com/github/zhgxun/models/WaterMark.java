package com.github.zhgxun.models;

/**
 * 加密水印
 */
public class WaterMark {

    /**
     * timestamp时间戳
     */
    private int timestamp;

    /**
     * 标识ID
     */
    private String appId;

    public WaterMark() {

    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
