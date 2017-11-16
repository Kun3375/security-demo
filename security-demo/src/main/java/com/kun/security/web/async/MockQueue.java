package com.kun.security.web.async;

import org.springframework.stereotype.Component;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 23:27
 */
@Component
public class MockQueue {

    private String placeOrder;
    private String completeOrder;
    
    public String getPlaceOrder() {
        return placeOrder;
    }
    
    public void setPlaceOrder(String placeOrder) {
        
        /*
         * 模拟订单处理服务器的操作
         */
        new Thread( () -> {
            
            System.out.println("接到下单请求" + placeOrder);
            this.placeOrder = placeOrder;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            System.out.println("处理请求完毕 " + placeOrder);
            
        }).start();
    }
    
    public String getCompleteOrder() {
        return completeOrder;
    }
    
    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
