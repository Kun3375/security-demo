package com.kun.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/3 10:26
 */
@Component
public class SmsCaptchaAuthenticationSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure(HttpSecurity builder) throws Exception {
    
        SmsCaptchaAuthenticationFilter filter = new SmsCaptchaAuthenticationFilter();
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        
        SmsCaptchaAuthenticationProvider provider = new SmsCaptchaAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        
        builder.authenticationProvider(provider).addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
