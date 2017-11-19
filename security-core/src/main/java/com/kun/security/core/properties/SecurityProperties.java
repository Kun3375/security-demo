package com.kun.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 21:59
 */
@ConfigurationProperties(prefix = "kun.security")
public class SecurityProperties {
    
    private CommonProperties common = new CommonProperties();
    private BrowserProperties browser = new BrowserProperties();
    
    public CommonProperties getCommon() {
        return common;
    }
    
    public void setCommon(CommonProperties common) {
        this.common = common;
    }
    
    public BrowserProperties getBrowser() {
        return browser;
    }
    
    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
    
    
}
