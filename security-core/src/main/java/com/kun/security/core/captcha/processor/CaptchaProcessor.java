package com.kun.security.core.captcha.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/27 22:09
 */
public interface CaptchaProcessor {

    void create(ServletWebRequest request, String captchaType) throws Exception;

}
