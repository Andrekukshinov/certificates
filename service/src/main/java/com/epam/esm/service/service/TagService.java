package com.epam.esm.service.service;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ValidationException;

public interface TagService {
    void saveTag(TagDto tag) throws ValidationException;

    void deleteTag(Long tagId);

    TagDto getTag(Long id);
}
