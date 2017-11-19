package com.kun.security.browser;

import com.kun.security.browser.support.SimpleResult;
import com.kun.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 21:44
 */
@RestController
public class BrowserSecurityController {
    
    private static final Logger log = LoggerFactory.getLogger(BrowserSecurityController.class);
    
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    private SecurityProperties securityProperties;
    
    /**
     * 当需要身份认证时，指定跳转的地址
     * 页面请求，定向至登陆页面；其他请求，返回401状态码和信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResult requireAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws IOException {
        // 得到被缓存的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            log.info("目标地址：{}", target);
            // 页面请求，返回登陆页面
            if (StringUtils.endsWith(target, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        // 其他请求，返回信息和401状态码
        return new SimpleResult("访问的服务需要身份认证，需要引导用户至登陆页");
    }
    
    
}
