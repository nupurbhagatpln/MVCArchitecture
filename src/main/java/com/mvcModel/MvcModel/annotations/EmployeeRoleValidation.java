package com.mvcModel.MvcModel.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidation implements ConstraintValidator<EmployeeRoleValidationAnnotation,String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roleList= List.of("User","Admin");
        return roleList.contains(inputRole);
    }
}
