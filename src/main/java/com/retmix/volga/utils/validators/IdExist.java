package com.retmix.volga.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdExistValidator.class)
public @interface IdExist {
    String message() default "Foreign key does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends JpaRepository<?,?>> repository();
}
