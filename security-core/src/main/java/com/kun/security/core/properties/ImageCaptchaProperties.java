package com.kun.security.core.properties;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/24 21:35
 */
public class ImageCaptchaProperties extends StandardCaptchaProperties{

    private int width = 67;
    private int height = 23;
    
    public ImageCaptchaProperties() {
        setLength(4);
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
}
