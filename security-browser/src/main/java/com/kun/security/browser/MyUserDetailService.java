package com.kun.security.browser;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/16 19:37
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库，通过用户名得到密码及权限
        return new User(
                "kun", // 用户名
                "$2a$10$COHPGeNDbIC/QZGIH3339.N8S391mZaj5B5/MyfhilHQ4GnEmcTh6", // 加密密码
                true, // 有效
                true, // 账户未过期
                true, // 密码未过期
                true, // 账户未锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")); // 权限
    }
}
