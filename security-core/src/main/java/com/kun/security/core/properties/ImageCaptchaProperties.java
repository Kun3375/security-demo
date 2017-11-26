package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 21:35
 */
public class ImageCaptchaProperties {

    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireTime = 60;
    private String urls;
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
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
