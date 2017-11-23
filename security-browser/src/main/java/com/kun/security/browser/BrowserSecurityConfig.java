package com.kun.security.browser;

import com.kun.security.core.captcha.CaptchaValidationFilter;
import com.kun.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/15 20:10
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private CaptchaValidationFilter captchaValidationFilter;
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // 密码加解密方式
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 在验证用户名密码之前验证验证码
                .addFilterBefore(captchaValidationFilter, UsernamePasswordAuthenticationFilter.class)
                // 设置为表单登陆
                .formLogin()
                // 设置登陆页面
                .loginPage("/authentication/require")
                // 设置登陆表单提交路径
                .loginProcessingUrl(securityProperties.getCommon().getLoginProcessingUrl())
                // 登陆成功处理（默认跳转至原目标）
                .successHandler(myAuthenticationSuccessHandler)
                // 登陆失败处理（默认跳转至登陆表单）
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                // 不需要身份认证的页面
                .antMatchers(
                        // 默认的验证跳转路径
                        "/authentication/require",
                        // 配置的登录页面
                        securityProperties.getBrowser().getLoginPage(),
                        // 验证码路径
                        "/captcha/image"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
