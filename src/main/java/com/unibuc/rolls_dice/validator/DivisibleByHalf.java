package com.unibuc.rolls_dice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DivisibleByHalfValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DivisibleByHalf {
    String message() default "Value must be divisible by 0.5.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
