package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 22:55
 */
public class CommonProperties {
    
    private String loginProcessingUrl = "/authentication/form";
    private AuthHandleType authHandleType = AuthHandleType.JSON;
    private int tokenValiditySeconds = 3600;
    private String rememberMeName = "remember-me";
    
    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }
    
    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }
    
    public AuthHandleType getAuthHandleType() {
        return authHandleType;
    }
    
    public void setAuthHandleType(AuthHandleType authHandleType) {
        this.authHandleType = authHandleType;
    }
    
    public int getTokenValiditySeconds() {
        return tokenValiditySeconds;
    }
    
    public void setTokenValiditySeconds(int tokenValiditySeconds) {
        this.tokenValiditySeconds = tokenValiditySeconds;
    }
    
    public String getRememberMeName() {
        return rememberMeName;
    }
    
    public void setRememberMeName(String rememberMeName) {
        this.rememberMeName = rememberMeName;
    }
}
