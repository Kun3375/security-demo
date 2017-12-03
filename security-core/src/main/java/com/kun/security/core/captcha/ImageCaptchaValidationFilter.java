package com.kun.security.core.captcha;

import com.kun.security.core.captcha.processor.AbstractCaptchaProcessor;
import com.kun.security.core.captcha.type.ImageCaptcha;
import com.kun.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 23:03
 */
@Component
public class ImageCaptchaValidationFilter extends OncePerRequestFilter implements InitializingBean {
    
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private Set<String> urls = new HashSet<>();
    
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                securityProperties.getCaptcha().getImageCaptcha().getUrls(), ",");
        if (configUrls != null) {
            Collections.addAll(urls, configUrls);
        }
        urls.add(securityProperties.getCommon().getUsernamePasswordLoginProcessingUrl());
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        
        if (action) {
            try {
                validate(new ServletWebRequest(request, response));
            } catch (CaptchaValidationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // session中存储的验证码正确值
        ImageCaptcha captchaInSession =
                (ImageCaptcha) sessionStrategy.getAttribute(
                        request, AbstractCaptchaProcessor.SESSION_KEY_PREFIX + "IMAGE");
        // 请求中输入的验证码
        String captchaInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCaptcha");
        
        if (captchaInSession == null) {
            throw new CaptchaValidationException("验证码不存在");
        }
        
        sessionStrategy.removeAttribute(request, AbstractCaptchaProcessor.SESSION_KEY_PREFIX + "IMAGE");
        
        if (StringUtils.isBlank(captchaInRequest)) {
            throw new CaptchaValidationException("验证码不能为空");
        }
        if (captchaInSession.isExpired()) {
            throw new CaptchaValidationException("验证码已过期");
        }
        if (!StringUtils.equals(captchaInSession.getCode(), captchaInRequest)) {
            throw new CaptchaValidationException("验证码不正确");
        }
        
        
    }
    
    
}
