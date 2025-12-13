package com.mvcModel.MvcModel.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidation.class})
public @interface EmployeeRoleValidationAnnotation {

    String message() default "Role should be either Admin or User";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
