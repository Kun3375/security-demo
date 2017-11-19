package com.kun.security.core;

import com.kun.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/19 22:02
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
    
}
