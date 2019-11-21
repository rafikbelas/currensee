package com.rafikbelas.currensee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

public class CurrencyCodeValidator implements ConstraintValidator<CurrencyCodeConstraint, String> {

    @Override
    public void initialize(CurrencyCodeConstraint validCurrencyCode) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Currency.getInstance(value);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
