package ru.unibell.unibelltestex.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.unibell.unibelltestex.util.ExceptionMessage;
import ru.unibell.unibelltestex.validation.validator.ContactTypeValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {ContactTypeValidator.class})
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface ContactTypeValidation {

    String message() default ExceptionMessage.NON_VALID_CONTACT_TYPE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}