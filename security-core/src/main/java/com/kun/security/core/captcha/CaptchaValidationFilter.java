package com.kun.security.core.captcha;

import com.kun.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 23:03
 */
@Component
public class CaptchaValidationFilter extends OncePerRequestFilter {
    
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 仅在登录请求时做验证码校验
        if (StringUtils.equals(securityProperties.getCommon().getLoginProcessingUrl(), request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
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
                (ImageCaptcha) sessionStrategy.getAttribute(request, CaptchaValidationController.SESSION_CAPTCHA_KEY);
        // 请求中输入的验证码
        String captchaInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "captcha");
    
        if (StringUtils.isBlank(captchaInRequest)) {
            throw new CaptchaValidationException("验证码不能为空");
        }
        if (captchaInSession == null) {
            throw new CaptchaValidationException("验证码不存在");
        }
        if (captchaInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, CaptchaValidationController.SESSION_CAPTCHA_KEY);
            throw new CaptchaValidationException("验证码已过期");
        }
        if (!StringUtils.equals(captchaInSession.getCode(), captchaInRequest)) {
            throw new CaptchaValidationException("验证码不正确");
        }
    
    
    }
    
    
}
