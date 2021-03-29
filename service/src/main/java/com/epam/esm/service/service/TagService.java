package com.epam.esm.service.service;

import com.epam.esm.persistence.entity.Tag;

import java.util.Optional;

public interface TagService {
    void saveTag(Tag tag);

    void deleteTag(Long tagId);

    Optional<Tag> getTag(Long id);
}
