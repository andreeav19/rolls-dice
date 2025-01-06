package com.unibuc.rolls_dice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EventTimeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTime {
    String message() default "Event time cannot be earlier than the current date.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
