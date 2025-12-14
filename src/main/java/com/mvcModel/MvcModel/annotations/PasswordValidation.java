package com.mvcModel.MvcModel.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<PasswordValidationAnnotation, String> {
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
       return (str.matches(".*[A-Z].*") && str.matches(".*[a-z].*")
               && str.matches(".*[0-9].*")
                && str.matches(".*[^a-zA-Z0-9].*")
               && str.length()>=10);
    }
}
