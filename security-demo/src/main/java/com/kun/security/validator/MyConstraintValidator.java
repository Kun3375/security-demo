package com.kun.security.validator;

import com.kun.security.service.MyConstraintService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 13:04
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
    
    @Autowired
    private MyConstraintService myConstraintService;
    
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my constraint validator is initializing");
        if(myConstraintService != null){
            System.out.println("my constraint service inject succeed");
        } else {
            System.out.println("my constraint service inject failed");
        }
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("validate can not pass");
        return false;
    }
}
