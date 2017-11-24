package com.kun.security.core.captcha;

import com.kun.security.core.captcha.generator.CaptchaGenerator;
import com.kun.security.core.captcha.generator.DefaultCaptchaGenerator;
import com.kun.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 22:27
 */
@Configuration
public class CaptchaConfig {
    
    @Autowired
    private SecurityProperties securityProperties;
    
    @Bean
    @ConditionalOnMissingBean(CaptchaGenerator.class)
    public CaptchaGenerator getCaptchaGenerator() {
        DefaultCaptchaGenerator captchaGenerator = new DefaultCaptchaGenerator();
        captchaGenerator.setSecurityProperties(securityProperties);
        return captchaGenerator;
    }
    
    
}
