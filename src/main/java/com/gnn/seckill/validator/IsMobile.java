package com.gnn.seckill.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 检验手机号格式,自定义注解
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {
    boolean required() default true ;

    String message() default "手机号码格式有误!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
