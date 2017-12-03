package com.kun.security.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 13:03
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
    
    String message() default "my constraint default error message";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
