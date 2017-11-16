package com.kun.security.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 0:17
 */
public class UserCondition {
    
    @ApiModelProperty("用户名")
    private String username;
    private int age;
    private int ageTo;
    private String remark;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAgeTo() {
        return ageTo;
    }
    
    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
