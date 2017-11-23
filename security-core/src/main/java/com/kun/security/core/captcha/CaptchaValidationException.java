package com.kun.security.core.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义的验证码校验异常类，继承自SpringSecurity的身份验证异常抽象基类
 *
 * @author CaoZiye
 * @version 1.0 2017/11/23 23:10
 */
public class CaptchaValidationException extends AuthenticationException {
    
    private static final long serialVersionUID = -8840486870184440635L;
    
    public CaptchaValidationException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public CaptchaValidationException(String msg) {
        super(msg);
    }
}
