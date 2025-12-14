package com.mvcModel.MvcModel.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {PrimeNumberValidation.class})
public @interface PrimeNumberValidationAnnotation {
    String message() default "Enter correct prime number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
