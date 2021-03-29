package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.Tag;

import java.util.Set;

public interface TagRepository extends CRDRepository<Tag> {
    Tag findByName(String name);

    Set<Tag> findTagsByNames(Set<String> tagName);
}
