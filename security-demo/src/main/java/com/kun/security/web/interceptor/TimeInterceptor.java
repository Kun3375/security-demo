package com.kun.security.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 15:35
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object handler) throws Exception {
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
        System.out.println("enter interceptor");
        System.out.println("preHandle");
        System.out.println("method is " + ((HandlerMethod) handler).getMethod().getName());
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        System.out.println("posted, consume:"
                + (System.currentTimeMillis() - (Long) httpServletRequest.getAttribute("startTime")));
    }
    
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        //如果使用ControllerAdvice，异常将被处理，并不会被拦截器捕获
        if (ex != null) {
            System.out.println(ex.getMessage());
        }
        System.out.println("completed, consume:"
                + (System.currentTimeMillis() - (Long) httpServletRequest.getAttribute("startTime")));
    }
}
