package com.kun.security.core.social.qq.connect;

import com.kun.security.core.social.qq.api.QQ;
import com.kun.security.core.social.qq.api.QQUserInfo;
import com.kun.security.core.social.qq.api.QQimpl;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/7 0:35
 */
public class QQAdapter implements ApiAdapter<QQ> {
    
    @Override
    public boolean test(QQ api) {
        return true;
    }
    
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurlQq1());
        values.setProfileUrl(null);
        values.setProviderUserId(((QQimpl)api).getOpenId());
    }
    
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }
    
    @Override
    public void updateStatus(QQ api, String message) {
    
    }
}
