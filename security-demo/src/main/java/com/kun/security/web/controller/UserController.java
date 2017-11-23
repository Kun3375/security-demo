package com.kun.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kun.security.dto.User;
import com.kun.security.dto.UserCondition;
import com.kun.security.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 0:04
 */
@RestController
@RequestMapping("/users")
@EnableSwagger2
public class UserController {
    
    /**
     * 获得用户认证全部信息
     * @param authentication
     * @return
     */
    @GetMapping("/me")
    public Authentication getAuthentication(Authentication authentication) {
        return authentication;
    }
    
    /**
     * 获得用户认证基本信息
     * @param userDetails
     * @return
     */
    @GetMapping("/my_auth")
    public UserDetails getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
    
    /**
     * 新增用户
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "创建用户")
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
    
        if (bindingResult.hasFieldErrors()) {
            bindingResult.getFieldErrors().forEach((error) ->
                System.out.println(error.getField() + " " + error.getDefaultMessage())
            );
        }
        
        System.out.println(user);
        user.setId(10);
        return user;
    }
    
    /**
     * 查询用户列表
     * @param userCondition
     * @param pageable
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "查询用户列表")
    public List<User> query(UserCondition userCondition,
                            @PageableDefault(size = 20, sort = {"username", "age"}, direction = Sort.Direction.DESC)
                                    Pageable pageable) {
        
        System.out.println(ReflectionToStringBuilder.toString(userCondition, ToStringStyle.MULTI_LINE_STYLE));
        
        System.out.println(pageable.getOffset());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.get(0).setUsername("kun");
        users.get(0).setPassword("3375");
        users.add(new User());
        users.add(new User());
        return users;
    }
    
    /**
     * 查询单用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam("用户编号") @PathVariable("id") Integer id) {
        System.out.println("get info");
        System.out.println(id);
    
        if (id > 10) {
            throw new UserNotExistException(id);
        }
        
        User user = new User();
        user.setUsername("kun");
        user.setPassword("3375");
        return user;
    }
    
    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable("id") String id) {
        System.out.println(id);
    }
    
}
