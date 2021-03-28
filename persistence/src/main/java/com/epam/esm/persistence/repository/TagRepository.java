package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface TagRepository extends CRDRepository<Tag> {
    Tag findByName(String name);

    Set<Tag> findTagsByNames(Set<String> tagName);

    Set<Tag> findCertificateTags(Long certificateId);

    void deleteCertificateTags(Long certificateId);
}
