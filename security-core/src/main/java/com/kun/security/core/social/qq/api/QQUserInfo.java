package com.kun.security.core.social.qq.api;

/**
 * @author CaoZiye
 * @version 1.0 2017/12/6 23:43
 */
public class QQUserInfo {
    
    /**
     * 返回码
     */
    private Integer ret;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * QQ空间昵称
     */
    private String nickname;
    /**
     * 大小为30×30像素的QQ空间头像URL
     */
    private String figureurl;
    /**
     * 大小为50×50像素的QQ空间头像URL
     */
    private String figureurl1;
    /**
     * 大小为100×100像素的QQ空间头像URL
     */
    private String figureurl2;
    /**
     * 大小为40×40像素的QQ头像URL
     */
    private String figureurlQq1;
    /**
     * 大小为100×100像素的QQ头像URL,不一定存在
     */
    private String figureurlQq2;
    /**
     * 性别，默认值“男”
     */
    private Character gender;
    /**
     * 标识用户是否为黄钻用户（0：不是；1：是）
     */
    private Character isYellowVip;
    /**
     *	标识用户是否为会员用户（0：不是；1：是）
     */
    private Character vip;
    /**
     * 黄钻等级
     */
    private Integer yellowVipLevel;
    /**
     * 会员等级
     */
    private Integer level;
    /**
     * 标识用户是否为年费黄钻用户（0：不是；1：是）
     */
    private Character isYellowYearVip;
    
    public Integer getRet() {
        return ret;
    }
    
    public void setRet(Integer ret) {
        this.ret = ret;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getFigureurl() {
        return figureurl;
    }
    
    public void setFigureurl(String figureurl) {
        this.figureurl = figureurl;
    }
    
    public String getFigureurl1() {
        return figureurl1;
    }
    
    public void setFigureurl1(String figureurl1) {
        this.figureurl1 = figureurl1;
    }
    
    public String getFigureurl2() {
        return figureurl2;
    }
    
    public void setFigureurl2(String figureurl2) {
        this.figureurl2 = figureurl2;
    }
    
    public String getFigureurlQq1() {
        return figureurlQq1;
    }
    
    public void setFigureurlQq1(String figureurlQq1) {
        this.figureurlQq1 = figureurlQq1;
    }
    
    public String getFigureurlQq2() {
        return figureurlQq2;
    }
    
    public void setFigureurlQq2(String figureurlQq2) {
        this.figureurlQq2 = figureurlQq2;
    }
    
    public Character getGender() {
        return gender;
    }
    
    public void setGender(Character gender) {
        this.gender = gender;
    }
    
    public Character getIsYellowVip() {
        return isYellowVip;
    }
    
    public void setIsYellowVip(Character isYellowVip) {
        this.isYellowVip = isYellowVip;
    }
    
    public Character getVip() {
        return vip;
    }
    
    public void setVip(Character vip) {
        this.vip = vip;
    }
    
    public Integer getYellowVipLevel() {
        return yellowVipLevel;
    }
    
    public void setYellowVipLevel(Integer yellowVipLevel) {
        this.yellowVipLevel = yellowVipLevel;
    }
    
    public Integer getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public Character getIsYellowYearVip() {
        return isYellowYearVip;
    }
    
    public void setIsYellowYearVip(Character isYellowYearVip) {
        this.isYellowYearVip = isYellowYearVip;
    }
}
