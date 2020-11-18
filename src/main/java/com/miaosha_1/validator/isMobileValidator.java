package com.miaosha_1.validator;

import com.miaosha_1.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author qqtang
 * @Date 2020/11/17 19:25
 * @Desc
 */
public class isMobileValidator implements ConstraintValidator <isMobile,String>{

    private boolean required = false;
    @Override
    public void initialize(isMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
       if (required){
           return ValidatorUtil.isMobile(s);
       }else {
           if (StringUtils.isEmpty(s)){
               return true;
           }else {
               return ValidatorUtil.isMobile(s);
           }
       }
    }
}
