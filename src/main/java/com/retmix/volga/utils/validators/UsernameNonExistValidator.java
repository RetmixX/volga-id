package com.retmix.volga.utils.validators;

import com.retmix.volga.shared.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public class UsernameNonExistValidator implements ConstraintValidator<UsernameNotExist, Object> {
    private UserRepository jpaRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return jpaRepository.findUserByUsername((String) value).isEmpty();
    }

    @Override
    public void initialize(UsernameNotExist constraintAnnotation) {
        jpaRepository = applicationContext.getBean(UserRepository.class);
    }
}
