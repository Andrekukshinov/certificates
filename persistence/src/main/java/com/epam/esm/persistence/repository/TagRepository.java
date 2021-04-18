package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.Tag;

import java.util.Optional;
import java.util.Set;

/**
 * Interface for executing operations with Tag entity within data source
 */
public interface TagRepository extends CRDRepository<Tag> {
    /**
     * Method for returning set of tags found by names from data source
     *
     * @param tagNames names to look for with
     * @return set of found tags
     */
    Set<Tag> findTagsByNames(Set<String> tagNames);


    /**
     * Method for getting tag found by name from data source
     *
     * @param tagName name to look for with
     * @return optional of tag
     */
    Optional<Tag> findByName(String tagName);

    /**Method that returns all tags from data source
     * @return list of tags
     */
    Set<Tag> findAll();
}
