package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 21:36
 */
public class CaptchaProperties {
    
    private ImageCaptchaProperties image = new ImageCaptchaProperties();
    
    public ImageCaptchaProperties getImage() {
        return image;
    }
    
    public void setImage(ImageCaptchaProperties image) {
        this.image = image;
    }
}
