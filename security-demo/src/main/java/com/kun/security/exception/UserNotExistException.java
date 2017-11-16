package com.kun.security.exception;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 14:07
 */
public class UserNotExistException extends RuntimeException {
    
    private static final long serialVersionUID = -1760266737017825446L;
    
    private Integer id;
    
    public UserNotExistException(Integer id) {
        super("用户不存在");
        this.id = id;
    }
    
    public UserNotExistException(Integer id, String message) {
        super(message);
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
}
