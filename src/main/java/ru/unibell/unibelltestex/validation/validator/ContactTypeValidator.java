package ru.unibell.unibelltestex.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.unibell.unibelltestex.validation.ContactTypeValidation;

public class ContactTypeValidator implements ConstraintValidator<ContactTypeValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;
        return switch (value) {
            case "PHONE", "EMAIL" -> true;
            default -> false;
        };
    }
}
