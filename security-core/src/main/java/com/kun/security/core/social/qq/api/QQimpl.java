package com.kun.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/6 23:44
 */
public class QQimpl extends AbstractOAuth2ApiBinding implements QQ {
    
    private static final String URL_GET_USER_INFO
            = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    private static final String URL_GET_OPEN_ID
            = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private String appId;
    private String openId;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    public QQimpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        
        String url = String.format(URL_GET_OPEN_ID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        
        System.out.println(result);
        
        this.openId = StringUtils.substringBetween(result, "\"openid\"", "}");
    }
    
    public String getOpenId() {
        return openId;
    }
    
    @Override
    public QQUserInfo getUserInfo() {
        
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        
        System.out.println(result);
        
        try {
            return objectMapper.readValue(result, QQUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取用户数据失败");
        }
    }
}
