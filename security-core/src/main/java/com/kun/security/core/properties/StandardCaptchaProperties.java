package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 21:35
 */
public class StandardCaptchaProperties {
    
    private String parameterName = SecurityConstants.DEFAULT_PARAMETER_NAME_CAPTCHA_SMS;
    private int length = 6;
    private int expireTime = 60;
    private String uris;
    
    public String getParameterName() {
        return parameterName;
    }
    
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
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
    
    public String getUris() {
        return uris;
    }
    
    public void setUris(String uris) {
        this.uris = uris;
    }
}
