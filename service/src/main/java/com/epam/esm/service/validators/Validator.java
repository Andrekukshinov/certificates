package com.epam.esm.service.validators;

import com.epam.esm.service.exception.ValidationException;

public interface Validator<T> {
    void validate(T object) throws ValidationException;
}
