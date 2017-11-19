package com.kun.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kun.security.core.properties.AuthHandleType;
import com.kun.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 22:33
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    private static final Logger log = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
    
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;
    
    /**
     * 登陆成功时触发
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功");
        if (AuthHandleType.JSON == securityProperties.getCommon().getAuthHandleType()) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
