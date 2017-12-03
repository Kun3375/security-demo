package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 22:55
 */
public class CommonProperties {
    
    private String passwordLoginProcessingUrl = SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_PASSWORD;
    private String mobileLoginProcessingUrl = SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE;
    private AuthHandleType authHandleType = AuthHandleType.JSON;
    private int tokenValiditySeconds = SecurityConstants.DEFAULT_TOKEN_VALIDITY_SECONDS;
    private String rememberMeName = SecurityConstants.DEFAULT_REMEMBER_ME_NAME;
    
    public String getPasswordLoginProcessingUrl() {
        return passwordLoginProcessingUrl;
    }
    
    public void setPasswordLoginProcessingUrl(String passwordLoginProcessingUrl) {
        this.passwordLoginProcessingUrl = passwordLoginProcessingUrl;
    }
    
    public String getMobileLoginProcessingUrl() {
        return mobileLoginProcessingUrl;
    }
    
    public void setMobileLoginProcessingUrl(String mobileLoginProcessingUrl) {
        this.mobileLoginProcessingUrl = mobileLoginProcessingUrl;
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
