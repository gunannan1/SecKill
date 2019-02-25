package com.gnn.seckill.validator;

import com.gnn.seckill.annotation.MobileCheck;
import com.gnn.seckill.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号格式校验
 */
public class MobileValidator implements ConstraintValidator<MobileCheck, String> {

    private boolean require = false ;

    @Override
    public void initialize(MobileCheck isMobile) {
        require = isMobile.required() ;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(require){
            return ValidatorUtil.isMobile(value) ;
        }else{
            if(StringUtils.isEmpty(value)){
                return true ;
            }else {
                return ValidatorUtil.isMobile(value) ;
            }
        }
    }
}
