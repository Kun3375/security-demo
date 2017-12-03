package com.kun.security.core.captcha.type;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 20:34
 */
public class ImageCaptcha extends StandardCaptcha {
    
    private BufferedImage image;
    
    public ImageCaptcha(String code, BufferedImage image, int expireTime) {
        super(code, expireTime);
        this.image = image;
    }
    
    public ImageCaptcha(String code, BufferedImage image, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}
