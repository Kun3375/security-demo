package com.kun.security.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 23:14
 */
@RestController
public class AsyncController {
    
    private static final Logger log = LoggerFactory.getLogger(AsyncController.class);
    
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    
    @RequestMapping("/callable")
    public Callable<String> callable() {
        log.info("主线程开始");
        
        Callable<String> result = () -> {
            log.info("副线程开始");
            Thread.sleep(3000);
            log.info("副线程结束");
            return "success";
        };
        
        log.info("主线程结束");
        return result;
    }
    
    @RequestMapping("/deferred")
    public DeferredResult<String> deferredResult() throws Exception {
        log.info("主线程开始");
        
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        
        log.info("主线程结束");
        return result;
    }
    
    
}
