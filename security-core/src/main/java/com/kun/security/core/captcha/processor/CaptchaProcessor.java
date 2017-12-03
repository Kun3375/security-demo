package com.kun.security.core.captcha.processor;

import com.kun.security.core.captcha.type.Captcha;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/27 22:09
 */
public interface CaptchaProcessor<C extends Captcha> {
    
    void create(ServletWebRequest request, String captchaType) throws Exception;
    
    void validate(ServletWebRequest request) throws ServletRequestBindingException;
}
