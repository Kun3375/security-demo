package com.kun.security.config;

import com.kun.security.web.filter.TimeFilter;
import com.kun.security.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.Executors;

/**
 * 使用@Configuration+@Bean可以注册bean
 * 继承WebMvcConfigurerAdapter可以便捷地注册interceptor,viewResolver等
 *
 * @author CaoZiye
 * @version 1.0 2017/11/12 15:11
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    
    @Autowired
    private TimeInterceptor timeInterceptor;
    
    /**
     * 当controller需要异步支持时配置。
     * 可以注册callable/deferredResult支持的异步拦截器，默认超时时间，以及使用的线程池
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(5)));
    }
    
    /**
     * 使用@Component+@Configuration+WebMvcConfigurerAdapter添加interceptor
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
    
    /**
     * 使用@Component注册filter，
     * 或者通过@Configuration+@Bean注册filter，配置灵活
     * @return 过滤器注册器
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        
        // 可关注filter配置
        HashSet<String> urls = new LinkedHashSet<>();
        urls.add("/*");
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        filterRegistrationBean.setAsyncSupported(true);
        filterRegistrationBean.setUrlPatterns(urls);
        filterRegistrationBean.setName("timeFilter");
        filterRegistrationBean.setEnabled(true);
        
        return filterRegistrationBean;
    }

}
