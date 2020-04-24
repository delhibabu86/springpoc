package com.example.demo.validator;

import com.example.demo.annotation.AlphaNumericValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class AlphaNumericValidation implements ConstraintValidator<AlphaNumericValidator, String> {

    @Override
    public void initialize(AlphaNumericValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
