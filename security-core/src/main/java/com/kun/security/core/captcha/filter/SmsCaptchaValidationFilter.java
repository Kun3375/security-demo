package com.kun.security.core.captcha.filter;

import com.kun.security.core.captcha.processor.CaptchaProcessor;
import com.kun.security.core.captcha.type.StandardCaptcha;
import com.kun.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 23:03
 */
@Component
public class SmsCaptchaValidationFilter extends CaptchaValidationFilter implements InitializingBean {
    
    @Autowired
    private CaptchaProcessor<StandardCaptcha> captchaProcessor;
    
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        addUriToMap(securityProperties.getCaptcha().getSmsCaptcha().getUris());
        uriSet.add(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE);
    }
    
    
    @Override
    protected void validate(ServletWebRequest request) throws ServletRequestBindingException {
        captchaProcessor.validate(request);
    }
}
