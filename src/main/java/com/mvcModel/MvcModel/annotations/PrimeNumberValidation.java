package com.mvcModel.MvcModel.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidation implements ConstraintValidator<PrimeNumberValidationAnnotation,Long> {
    @Override
    public boolean isValid(Long input, ConstraintValidatorContext constraintValidatorContext) {
        return (input%2==0);
    }
}
