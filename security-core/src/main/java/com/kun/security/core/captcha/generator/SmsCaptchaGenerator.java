package com.kun.security.core.captcha.generator;

import com.kun.security.core.captcha.type.StandardCaptcha;
import com.kun.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 22:25
 */
@Component("smsCaptchaGenerator")
public class SmsCaptchaGenerator implements CaptchaGenerator<StandardCaptcha> {
    
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public StandardCaptcha createCaptcha(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCaptcha().getStandardCaptcha().getLength());
        return new StandardCaptcha(code, securityProperties.getCaptcha().getStandardCaptcha().getExpireTime());
    }
    
}
