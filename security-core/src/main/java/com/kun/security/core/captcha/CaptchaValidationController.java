package com.kun.security.core.captcha;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 20:55
 */
@RestController
public class CaptchaValidationController {
    
    static final String SESSION_CAPTCHA_KEY = "SESSION_CAPTCHA";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    @GetMapping("/captcha/image")
    public void getImageCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成验证码
        ImageCaptcha imageCaptcha = createImageCaptcha(request);
        // 存储至session
        sessionStrategy.setAttribute(new ServletWebRequest(request, response), SESSION_CAPTCHA_KEY, imageCaptcha);
        // 输出验证码图片
        ImageIO.write(imageCaptcha.getImage(), "jpeg", response.getOutputStream());
    }
    
    private ImageCaptcha createImageCaptcha(HttpServletRequest request) {
        int width = 67;
        int height = 23;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
        Random random = new Random();
        Graphics graphics = bufferedImage.getGraphics();
        
        graphics.setColor(getRandomColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        graphics.setColor(getRandomColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
    
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sb.append(rand);
            graphics.setColor(new Color(
                    20 + random.nextInt(110),
                    20 + random.nextInt(110),
                    20 + random.nextInt(110)));
            graphics.drawString(rand, 13 * i + 6, 16);
        }
    
        graphics.dispose();
        
        return new ImageCaptcha(sb.toString(), bufferedImage, 60);
    }
    
    private Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    
    
    
    
    
    
    
}
