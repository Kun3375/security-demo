package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 21:36
 */
public class CaptchaProperties {
    
    private StandardCaptchaProperties smsCaptcha = new StandardCaptchaProperties();
    private ImageCaptchaProperties imageCaptcha = new ImageCaptchaProperties();
    
    public StandardCaptchaProperties getSmsCaptcha() {
        return smsCaptcha;
    }
    
    public void setSmsCaptcha(StandardCaptchaProperties smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }
    
    public ImageCaptchaProperties getImageCaptcha() {
        return imageCaptcha;
    }
    
    public void setImageCaptcha(ImageCaptchaProperties imageCaptcha) {
        this.imageCaptcha = imageCaptcha;
    }
}
