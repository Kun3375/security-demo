package com.kun.security.core.captcha.processor;

import com.kun.security.core.captcha.support.sms.SmsCaptchaSender;
import com.kun.security.core.captcha.type.StandardCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/27 22:38
 */
@Component
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor<StandardCaptcha> {
    
    @Autowired
    private SmsCaptchaSender smsCaptchaSender;
    
    @Override
    protected void send(ServletWebRequest request, StandardCaptcha captcha) throws IOException, ServletRequestBindingException {
        // should send message captcha
        smsCaptchaSender.send(ServletRequestUtils.getRequiredStringParameter(
                request.getRequest(), "mobile"),
                captcha.getCode());
    }
}
