package com.kun.security.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.kun.security.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 0:06
 */
public class User {
    
    public interface UserSimpleView {}
    
    public interface UserDetailView extends UserSimpleView {}
    
    @JsonView(UserSimpleView.class)
    private Integer id;
    
    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "用户名不正确")
    private String username;
    
    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(hidden = true)
    private String password;
    
    @JsonView(UserSimpleView.class)
    @Past(message = "生日必须为过去时间")
    private Date birthday;
    
    public User() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
