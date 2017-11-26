package com.kun.security.core.captcha.support.sms;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/26 23:19
 */
public interface SmsCaptchaSender {

    void send(String mobile, String code);

}
