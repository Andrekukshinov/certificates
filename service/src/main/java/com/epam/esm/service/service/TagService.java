package com.epam.esm.service.service;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ValidationException;

/**
 * Interface for performing business logics for Tag and Tag Dtos
 */
public interface TagService {
    /**
     * Method that performs validation for given dto object and saving of tag
     * @param tag dto to be validated and performed logics with
     * @throws ValidationException in case of validation error occur
     */
    void saveTag(TagDto tag) throws ValidationException;

    /**
     * Method that deletes object from system
     * @param tagId object id to perform logics with
     */
    void deleteTag(Long tagId);

    /**
     * Method that returns tag dto based on received id
     * @param id to find tag with
     * @throws com.epam.esm.service.exception.EntityNotFoundException if entity with id not exists
     * @return tag dto entity with specified id
     */
    TagDto getTag(Long id);
}
