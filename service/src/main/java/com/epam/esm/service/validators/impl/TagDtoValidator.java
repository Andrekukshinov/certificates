package com.epam.esm.service.validators.impl;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ValidationException;
import com.epam.esm.service.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class TagDtoValidator implements Validator<TagDto> {

    private static final int MAX_NAME_LENGTH = 50;

    @Override
    public void validate(TagDto object) throws ValidationException {
        if (object == null) {
            throw new ValidationException("object should exist!");
        }

        validateName(object.getName());
    }

    private void validateName(String name) throws ValidationException {
        if (name != null && name.length() > MAX_NAME_LENGTH) {
            throw new ValidationException("name cannot be longer than 50 symbols!");
        }
    }
}
