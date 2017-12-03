package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 21:59
 */
public class BrowserProperties {
    
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    
    public String getLoginPage() {
        return loginPage;
    }
    
    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
