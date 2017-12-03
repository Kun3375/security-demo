package com.kun.security.core.authentication;

import com.kun.security.core.properties.SecurityConstants;
import com.kun.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/3 14:32
 */
public class CommonSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;
    
    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(securityProperties.getCommon().getPasswordLoginProcessingUrl())
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
    }
    
}
