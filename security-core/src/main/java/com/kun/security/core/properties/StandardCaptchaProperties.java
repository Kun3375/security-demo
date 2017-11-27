package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 21:35
 */
public class StandardCaptchaProperties {

    private int length = 6;
    private int expireTime = 60;
    private String urls;
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public int getExpireTime() {
        return expireTime;
    }
    
    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
    
    public String getUrls() {
        return urls;
    }
    
    public void setUrls(String urls) {
        this.urls = urls;
    }
}
