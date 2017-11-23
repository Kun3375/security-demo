package com.kun.security.core.captcha;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 20:55
 */
@RestController
public class CaptchaValidationController {
    
    private static final String SESSION_CAPTCHA_KEY = "SESSION_CAPTCHA";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    @GetMapping("/captcha/image")
    public void getImageCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCaptcha imageCaptcha = createImageCaptcha(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request, response), SESSION_CAPTCHA_KEY, imageCaptcha);
        ImageIO.write(imageCaptcha.getImage(), "jpeg", response.getOutputStream());
    }
    
    private ImageCaptcha createImageCaptcha(HttpServletRequest request) {
        return null;
    }
    
    
}
