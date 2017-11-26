package com.kun.security.core.captcha.support.sms;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/26 23:21
 */
public class DefaultSmsCaptchaSender implements SmsCaptchaSender {
    
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机-" + mobile + "-发送验证码-" + code + "-");
    }
}
