package com.epam.esm.service.validators.impl;

import com.epam.esm.service.dto.GiftCertificateTagDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ValidationException;
import com.epam.esm.service.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class GiftCertificateTagDtoValidator implements Validator<GiftCertificateTagDto> {
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 255;

    private final Validator<TagDto> tagDtoValidator;

    @Autowired
    public GiftCertificateTagDtoValidator(Validator<TagDto> tagDtoValidator) {
        this.tagDtoValidator = tagDtoValidator;
    }

    @Override
    public void validate(GiftCertificateTagDto object) throws ValidationException {
        if (object == null) {
            throw new ValidationException("object should exist!");
        }

        validateId(object.getId());
        validateName(object.getName());
        validateDescription(object.getDescription());
        validateCreateDate(object.getCreateDate());
        validateLastUpdateDate(object.getLastUpdateDate());
        validateDuration(object.getDuration());
        validatePrice(object.getPrice());
        validateCertificateTags(object.getTags());
    }

    private void validateId(Long id) throws ValidationException {
        if (id != null && id < 0) {
            throw new ValidationException("id must be more than 0!");
        }
    }

    private void validateName(String name) throws ValidationException {
        if (name != null && name.length() > MAX_NAME_LENGTH) {
            throw new ValidationException("name cannot be longer than 50 symbols!");
        }
    }

    private void validateDescription(String description) throws ValidationException {
        if (description != null && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new ValidationException("description cannot be longer than 255 symbols!");
        }
    }

    private void validateCreateDate(LocalDateTime createDate) throws ValidationException {
        if (createDate != null) {
            throw new ValidationException("create date cannot be specified!");
        }
    }

    private void validateLastUpdateDate(LocalDateTime lastUpdateDate) throws ValidationException {
        if (lastUpdateDate != null) {
            throw new ValidationException("last update date cannot be specified!");
        }
    }

    private void validateDuration(Short duration) throws ValidationException {
        if (duration != null && duration < 0) {
            throw new ValidationException("duration cannot be negative!");
        }
    }

    private void validatePrice(BigDecimal price) throws ValidationException {
        if (price != null && price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("price cannot be negative!");
        }
    }

    private void validateCertificateTags(Set<TagDto> tags) throws ValidationException {
        if (tags == null) {
            return;
        }
        for (TagDto tag : tags) {
            tagDtoValidator.validate(tag);
        }

    }
}
