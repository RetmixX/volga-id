package com.retmix.volga.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public class IdExistValidator implements ConstraintValidator<IdExist, Object> {
    private JpaRepository<?, Serializable> jpaRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void initialize(IdExist constraintAnnotation) {
        Class<?> repositoryClass = constraintAnnotation.repository();
        jpaRepository = (JpaRepository<?, Serializable>) applicationContext.getBean(repositoryClass);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return jpaRepository.existsById((Serializable) value);
    }
}
