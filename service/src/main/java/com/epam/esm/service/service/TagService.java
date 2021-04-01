package com.epam.esm.service.service;

import com.epam.esm.service.dto.TagDto;

import java.util.Optional;

public interface TagService {
    void saveTag(TagDto tag);

    void deleteTag(Long tagId);

    Optional<TagDto> getTag(Long id);
}
