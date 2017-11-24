package com.kun.security.core.captcha;

import com.kun.security.core.captcha.generator.CaptchaGenerator;
import com.kun.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
    
    static final String SESSION_CAPTCHA_KEY = "SESSION_CAPTCHA";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private CaptchaGenerator captchaGenerator;
    
    @GetMapping("/captcha/image")
    public void getImageCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成验证码
        ImageCaptcha imageCaptcha = captchaGenerator.createImageCaptcha(request);
        // 存储至session
        sessionStrategy.setAttribute(new ServletWebRequest(request, response), SESSION_CAPTCHA_KEY, imageCaptcha);
        // 输出验证码图片
        ImageIO.write(imageCaptcha.getImage(), "jpeg", response.getOutputStream());
    }
    
}
