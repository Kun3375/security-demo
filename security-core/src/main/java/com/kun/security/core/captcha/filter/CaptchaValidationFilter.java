package com.kun.security.core.captcha.filter;

import com.kun.security.core.captcha.CaptchaValidationException;
import com.kun.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/23 23:03
 */
public abstract class CaptchaValidationFilter
        extends OncePerRequestFilter implements InitializingBean {
    
    /**
     * 配置信息
     */
    @Autowired
    protected SecurityProperties securityProperties;
    /**
     * 需要鉴权的uri集合
     */
    protected Set<String> uriSet = new HashSet<>();
    /**
     * 验证失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 路径匹配工具类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    
    /**
     * 将配置文件中的uri信息载入需要鉴权uri集合
     *
     * @param uriString 配置文件中的uri字符串
     */
    protected void addUriToMap(String uriString) {
        if (StringUtils.isNotBlank(uriString)) {
            String[] uris = StringUtils.splitByWholeSeparatorPreserveAllTokens(uriString, ",");
            uriSet.addAll(Arrays.asList(uris));
        }
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        boolean action = false;
        for (String url : uriSet) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        
        if (action) {
            logger.info("验证码校验请求地址：" + request.getRequestURI());
            try {
                validate(new ServletWebRequest(request, response));
            } catch (CaptchaValidationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    /**
     * 由子类完成验证逻辑
     * 子类调用相应的processor的验证方法
     *
     * @param request 包装的请求与响应
     * @throws ServletRequestBindingException
     */
    protected abstract void validate(ServletWebRequest request) throws ServletRequestBindingException;
    
}
