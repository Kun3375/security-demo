package com.kun.security.core.social.qq.connect;

import com.kun.security.core.social.qq.api.QQ;
import com.kun.security.core.social.qq.api.QQimpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/7 0:20
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
    
    private String appId;
    
    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }
    
    @Override
    public QQ getApi(String accessToken) {
        return new QQimpl(accessToken, appId);
    }
}
