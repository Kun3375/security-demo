package com.kun.security.browser.support;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 21:46
 */
public class SimpleResult {
    
    private Object content;
    
    public SimpleResult(Object content) {
        this.content = content;
    }
    
    public Object getContent() {
        return content;
    }
    
    public void setContent(Object content) {
        this.content = content;
    }
}
