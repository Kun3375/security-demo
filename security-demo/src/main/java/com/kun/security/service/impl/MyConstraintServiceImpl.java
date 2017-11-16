package com.kun.security.service.impl;

import com.kun.security.service.MyConstraintService;
import org.springframework.stereotype.Component;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 13:17
 */
@Component
public class MyConstraintServiceImpl implements MyConstraintService {
    
    @Override
    public void testExe() {
        System.out.println("my constraint service exe");
    }
}
