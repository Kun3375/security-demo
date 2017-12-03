package com.kun.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/2 19:23
 */
public class SmsCaptchaAuthenticationProvider implements AuthenticationProvider {
    
    private UserDetailsService userDetailsService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
    
        SmsCaptchaAuthenticationToken authenticationResult
                = new SmsCaptchaAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authentication.getDetails());
        
        return authenticationResult;
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
