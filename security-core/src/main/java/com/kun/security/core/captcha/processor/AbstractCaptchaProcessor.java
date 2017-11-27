package com.kun.security.core.captcha.processor;

import com.kun.security.core.captcha.generator.CaptchaGenerator;
import com.kun.security.core.captcha.type.Captcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/27 22:11
 */
public abstract class AbstractCaptchaProcessor<C extends Captcha> implements CaptchaProcessor {
    
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    public static final String SESSION_KEY_PREFIX = "SESSION_CAPTCHA_";
    private static final String CAPTCHA_GENERATOR = "CaptchaGenerator";
    private static final String CAPTCHA_IDENTIFIER_PREFIX = "/captcha/";
    @Autowired
    private CaptchaGenerator<C> captchaGenerator;
    
    @Override
    public void create(ServletWebRequest request) throws Exception {
        String captchaType = getCaptchaType(request);
        C captcha = generate(request);
        save(request, captchaType, captcha);
        send(request, captcha);
    }
    
    private void save(ServletWebRequest request, String captchaType, C captcha) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + captchaType.toUpperCase(), captcha);
    }
    
    protected abstract void send(ServletWebRequest request, C captcha) throws Exception;
    
    private C generate(ServletWebRequest request) {
        return captchaGenerator.createCaptcha(request.getRequest());
    }
    
    private String getCaptchaType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), CAPTCHA_IDENTIFIER_PREFIX);
    }
    
}
