package com.kun.security.core.captcha.processor;

import com.kun.security.core.captcha.type.ImageCaptcha;
import com.kun.security.core.properties.SecurityConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/27 22:35
 */
@Component
public class ImageCaptchaProcessor extends AbstractCaptchaProcessor<ImageCaptcha> {
    
    @Override
    protected void send(ServletWebRequest request, ImageCaptcha captcha) throws IOException {
        ImageIO.write(captcha.getImage(), "jpeg", request.getResponse().getOutputStream());
    }
    
    @Override
    protected String getSessionCaptchaKey() {
        return SESSION_CAPTCHA_KEY_PREFIX + "IMAGE";
    }
    
    @Override
    protected String getRequestCaptchaKey() {
        return SecurityConstants.DEFAULT_PARAMETER_NAME_CAPTCHA_IMAGE;
    }
    
}
