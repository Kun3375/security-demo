package com.kun.security.browser;

import com.kun.security.core.authentication.CommonSecurityConfig;
import com.kun.security.core.authentication.mobile.SmsCaptchaAuthenticationSecurityConfig;
import com.kun.security.core.captcha.config.ImageCaptchaSecurityConfig;
import com.kun.security.core.captcha.config.SmsCaptchaSecurityConfig;
import com.kun.security.core.captcha.filter.ImageCaptchaValidationFilter;
import com.kun.security.core.captcha.filter.SmsCaptchaValidationFilter;
import com.kun.security.core.properties.SecurityConstants;
import com.kun.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/15 20:10
 */
@Configuration
public class BrowserSecurityConfig extends CommonSecurityConfig {
    
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private ImageCaptchaValidationFilter imageCaptchaValidationFilter;
    @Autowired
    private SmsCaptchaValidationFilter smsCaptchaValidationFilter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ImageCaptchaSecurityConfig imageCaptchaSecurityConfig;
    @Autowired
    private SmsCaptchaSecurityConfig smsCaptchaSecurityConfig;
    @Autowired
    private SmsCaptchaAuthenticationSecurityConfig smsCaptchaAuthenticationSecurityConfig;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DataSource dataSource;
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // 密码加解密方式
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository getPersistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        applyPasswordAuthenticationConfig(http);
        
        http
                .apply(smsCaptchaSecurityConfig)
                .and()
                .apply(imageCaptchaSecurityConfig)
                .and()
                .apply(smsCaptchaAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                .rememberMeParameter(securityProperties.getCommon().getRememberMeName())
                .tokenRepository(getPersistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getCommon().getTokenValiditySeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(
                        // 默认的验证跳转路径
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        // 手机短信验证码登陆路径
                        securityProperties.getCommon().getMobileLoginProcessingUrl(),
                        // 配置的登录页面
                        securityProperties.getBrowser().getLoginPage(),
                        // 验证码路径
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        
    }
}
