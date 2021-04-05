package com.epam.esm.service.validators;

import com.epam.esm.service.exception.ValidationException;

/**
 * Interface for objects validation
 * @param <T> - object type to be validated
 */
public interface Validator<T> {
    /**
     * Method that performs validation logic for received object
     * @param object to be validated
     * @throws ValidationException in case of validation errors occur
     */
    void validate(T object) throws ValidationException;
}
