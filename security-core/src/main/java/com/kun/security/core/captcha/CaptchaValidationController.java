package com.kun.security.core.captcha;

import com.kun.security.core.captcha.processor.CaptchaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 20:55
 */
@RestController
public class CaptchaValidationController {
    
    private static final String CAPTCHA_PROCESSOR = "CaptchaProcessor";
    @Autowired
    private Map<String, CaptchaProcessor> captchaProcessorMap;
    
    @GetMapping("/captcha/{type}")
    public void createCaptcha(HttpServletRequest request,
                              HttpServletResponse response,
                              @PathVariable("type") String captchaType) throws Exception {
        captchaProcessorMap.get(captchaType + CAPTCHA_PROCESSOR).create(
                new ServletWebRequest(request, response), captchaType);
    }
    
}
