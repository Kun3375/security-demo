package com.kun.security.web.controller;

import com.kun.security.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 14:23
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    
    /**
     * 针对UserNotExistException的全局处理。
     *
     * 如果不适用全局异常处理，会使用SpringBoot默认方式BasicErrorController，
     * 如果请求头中Accept信息包含text/html则返回页面，否则返回JSON。
     * 返回的页面可以自定义，存放于classpath:resources/error/{HttpStatus}.html
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistException(UserNotExistException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", exception.getId());
        map.put("message", exception.getMessage());
        return map;
    }
    
}
