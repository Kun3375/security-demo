package com.kun.security.core.captcha.generator;

import com.kun.security.core.captcha.type.Captcha;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 22:23
 */
public interface CaptchaGenerator<C extends Captcha> {
    
    C createCaptcha(HttpServletRequest request);
    
}
