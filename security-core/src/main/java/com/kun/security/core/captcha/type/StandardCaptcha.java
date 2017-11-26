package com.kun.security.core.captcha.type;

import java.time.LocalDateTime;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/26 23:06
 */
public class StandardCaptcha {
    
    private String code;
    private LocalDateTime expireTime;
    
    public StandardCaptcha(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }
    
    public StandardCaptcha(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
