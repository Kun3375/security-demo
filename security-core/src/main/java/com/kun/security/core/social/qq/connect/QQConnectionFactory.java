package com.kun.security.core.social.qq.connect;

import com.kun.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/7 0:46
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
