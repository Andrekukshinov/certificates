package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.Tag;

import java.util.Set;

/**
 * Interface for executing operations with Tag entity within data source
 */
public interface TagRepository extends CRDRepository<Tag> {
    /**
     * Method for returning set of tags found by names from data source
     * @param tagNames names to look for with
     * @return set of found tags
     */
    Set<Tag> findTagsByNames(Set<String> tagNames);
}
