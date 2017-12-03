package com.kun.security.core.captcha.generator;

import com.kun.security.core.captcha.type.ImageCaptcha;
import com.kun.security.core.properties.SecurityProperties;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 22:25
 */
public class ImageCaptchaGenerator implements CaptchaGenerator<ImageCaptcha> {
    
    private SecurityProperties securityProperties;
    
    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }
    
    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
    
    @Override
    public ImageCaptcha createCaptcha(HttpServletRequest request) {
        int width = ServletRequestUtils.getIntParameter(
                request, "width", securityProperties.getCaptcha().getImageCaptcha().getWidth());
        int height = ServletRequestUtils.getIntParameter(
                request, "height", securityProperties.getCaptcha().getImageCaptcha().getHeight());
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
        for (int i = 0, length = securityProperties.getCaptcha().getImageCaptcha().getLength(); i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sb.append(rand);
            graphics.setColor(new Color(
                    20 + random.nextInt(110),
                    20 + random.nextInt(110),
                    20 + random.nextInt(110)));
            graphics.drawString(rand, 13 * i + 6, 16);
        }
        
        graphics.dispose();
        
        return new ImageCaptcha(sb.toString(),
                bufferedImage,
                securityProperties.getCaptcha().getImageCaptcha().getExpireTime());
    }
    
    private Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
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
