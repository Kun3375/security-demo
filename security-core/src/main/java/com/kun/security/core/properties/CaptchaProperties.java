package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 21:36
 */
public class CaptchaProperties {
    
    private StandardCaptchaProperties standardCaptcha = new StandardCaptchaProperties();
    private ImageCaptchaProperties imageCaptcha = new ImageCaptchaProperties();
    
    public StandardCaptchaProperties getStandardCaptcha() {
        return standardCaptcha;
    }
    
    public void setStandardCaptcha(StandardCaptchaProperties standardCaptcha) {
        this.standardCaptcha = standardCaptcha;
    }
    
    public ImageCaptchaProperties getImageCaptcha() {
        return imageCaptcha;
    }
    
    public void setImageCaptcha(ImageCaptchaProperties imageCaptcha) {
        this.imageCaptcha = imageCaptcha;
    }
}
