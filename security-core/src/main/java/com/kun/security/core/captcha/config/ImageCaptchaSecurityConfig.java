package com.kun.security.core.captcha.config;

import com.kun.security.core.captcha.filter.ImageCaptchaValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/3 14:49
 */
@Component
public class ImageCaptchaSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    
    @Autowired
    private ImageCaptchaValidationFilter imageCaptchaValidationFilter;
    
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(imageCaptchaValidationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
