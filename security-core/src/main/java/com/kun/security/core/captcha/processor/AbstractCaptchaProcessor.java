package com.kun.security.core.captcha.processor;

import com.kun.security.core.captcha.CaptchaValidationException;
import com.kun.security.core.captcha.generator.CaptchaGenerator;
import com.kun.security.core.captcha.type.Captcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/27 22:11
 */
public abstract class AbstractCaptchaProcessor<C extends Captcha> implements CaptchaProcessor<C> {
    
    public static final String SESSION_CAPTCHA_KEY_PREFIX = "SESSION_CAPTCHA_";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private CaptchaGenerator<C> captchaGenerator;
    
    @Override
    public void create(ServletWebRequest request, String captchaType) throws Exception {
        C captcha = generate(request);
        save(request, captchaType, captcha);
        send(request, captcha);
    }
    
    private void save(ServletWebRequest request, String captchaType, C captcha) {
        sessionStrategy.setAttribute(request, SESSION_CAPTCHA_KEY_PREFIX + captchaType.toUpperCase(), captcha);
    }
    
    protected abstract void send(ServletWebRequest request, C captcha) throws Exception;
    
    private C generate(ServletWebRequest request) {
        return captchaGenerator.createCaptcha(request.getRequest());
    }
    
    protected abstract String getSessionCaptchaKey();
    
    protected abstract String getRequestCaptchaKey();
    
    @Override
    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        
        String sessionCaptchaKey = getSessionCaptchaKey();
        // session中存储的验证码正确值
        @SuppressWarnings("unchecked")
        C captchaInSession = (C) sessionStrategy.getAttribute(
                request, sessionCaptchaKey);
        // 请求中输入的验证码
        String captchaInRequest = ServletRequestUtils.getStringParameter(
                request.getRequest(), getRequestCaptchaKey());
        
        if (captchaInSession == null) {
            throw new CaptchaValidationException("验证码不存在");
        }
        
        sessionStrategy.removeAttribute(request, sessionCaptchaKey);
        
        if (StringUtils.isBlank(captchaInRequest)) {
            throw new CaptchaValidationException("验证码不能为空");
        }
        if (captchaInSession.isExpired()) {
            throw new CaptchaValidationException("验证码已过期");
        }
        if (!StringUtils.equals(captchaInSession.getCode(), captchaInRequest)) {
            throw new CaptchaValidationException("验证码不正确");
        }
        
    }
    
}
