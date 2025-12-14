package com.mvcModel.MvcModel.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {PasswordValidation.class})
public @interface  PasswordValidationAnnotation {
    String message() default "Enter strong password having ^(A-Z)|(0-9)|(@,#,$,%,&,*)$";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
