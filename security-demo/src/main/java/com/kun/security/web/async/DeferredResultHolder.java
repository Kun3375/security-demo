package com.kun.security.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 23:31
 */
@Component
public class DeferredResultHolder {
    
    private Map<String, DeferredResult<String>> map = new HashMap<>();
    
    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }
    
}
