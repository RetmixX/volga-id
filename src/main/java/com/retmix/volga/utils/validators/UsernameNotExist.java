package com.retmix.volga.utils.validators;

import com.retmix.volga.shared.repositories.UserRepository;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameNonExistValidator.class)
public @interface UsernameNotExist {
    String message() default "Username already exist";

    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};
}
