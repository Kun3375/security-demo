package com.kun.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 19:38
 */
@Aspect
@Component
public class TimeAspect {
    
//    @Around(value = "args(user,..) && execution(* com.kun.security.web.controller.UserController.*(..))")
    @Around("execution(public * com.kun.security.web.controller.UserController.*(..))")
    public Object handleUserController(ProceedingJoinPoint pjp) throws Throwable {
        
        long start = System.currentTimeMillis();
        System.out.println("enter time aspect");
    
        for (Object o : pjp.getArgs()) {
            System.out.println("arg is " + o);
        }
        
        Object result = pjp.proceed();
    
        System.out.println("exit time aspect, consumed:" + (System.currentTimeMillis() - start));

        return result;
    }
    
}
