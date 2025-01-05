package com.unibuc.rolls_dice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DivisibleByHalfValidator implements ConstraintValidator<DivisibleByHalf, Float> {

    @Override
    public boolean isValid(Float aFloat, ConstraintValidatorContext constraintValidatorContext) {
        return aFloat % 0.5f == 0.0f;
    }
}
