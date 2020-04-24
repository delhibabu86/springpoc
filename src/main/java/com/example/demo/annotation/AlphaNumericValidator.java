package com.example.demo.annotation;

import com.example.demo.validator.AlphaNumericValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlphaNumericValidation.class)
public @interface AlphaNumericValidator {
    Class<?>[] groups() default {};

    String message() default "{alphanumeric.message}";

    Class<? extends Payload>[] payload() default {};
}
