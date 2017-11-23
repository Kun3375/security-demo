package com.kun.security.core.captcha;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 20:34
 */
public class ImageCaptcha {
    
    private String code;
    private BufferedImage image;
    private LocalDateTime expireTime;
    
    public ImageCaptcha(String code, BufferedImage image, int expireTime) {
        this.code = code;
        this.image = image;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }
    
    public ImageCaptcha(String code, BufferedImage image, LocalDateTime expireTime) {
        this.code = code;
        this.image = image;
        this.expireTime = expireTime;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public LocalDateTime getExpireTime() {
        return expireTime;
    }
    
    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
    
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
