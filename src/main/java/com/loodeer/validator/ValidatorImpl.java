package com.loodeer.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

        private Validator validator;

        public ValidationResult validate(Object bean) {

                ValidationResult validationResult = new ValidationResult();
                Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
                if (constraintViolationSet.size() > 0) {
                        validationResult.setHasErrors(true);
                        for(int i = 0; i < constraintViolationSet.size(); i ++) {
                                ConstraintViolation constraintViolation = constraintViolationSet.iterator().next();
                                validationResult.getErrorMsgMap().put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                        }
                }
                return validationResult;
        }

        @Override public void afterPropertiesSet() throws Exception {
                // 将 hibernate validator 通过工厂的初始化方法使其实例化
                this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
}
