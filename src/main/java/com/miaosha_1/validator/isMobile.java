package com.miaosha_1.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @author qqtang
 * @Date 2020/11/17 19:20
 * @Desc
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
public @interface isMobile {

    boolean required() default true;

    String message() default "手机格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
