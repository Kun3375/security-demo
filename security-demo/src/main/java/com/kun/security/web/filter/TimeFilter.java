package com.kun.security.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 14:53
 */
public class TimeFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println("enter time filter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("exit time filter, consumed:" + (System.currentTimeMillis() - start));
    }
    
    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
