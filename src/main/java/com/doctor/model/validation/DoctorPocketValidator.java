package com.doctor.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * The type Cre validator.
 */
public abstract class DoctorPocketValidator implements Validator {
    /**
     * The constant ERROR_FIELD_REQUIRED.
     */
    public static final String ERROR_FIELD_REQUIRED = "Please fill all the fields.";
    /**
     * The constant ERROR_FIELD_REQUIRED.
     */
    public static final String FAIL_LOGIN = "Fail login";



    /**
     * Reject empty field.
     *
     * @param errors    the errors
     * @param field     the field
     * @param errorCode the error code
     */
    protected void rejectEmptyField(Errors errors, String field, String errorCode) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode);
    }

    /**
     * Reject field.
     *
     * @param errors    the errors
     * @param field     the field
     * @param errorCode the error code
     */
    protected void rejectField(Errors errors, String field, String errorCode) {
        errors.rejectValue(field, errorCode);
    }
}

