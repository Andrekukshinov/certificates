package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.Tag;
import org.springframework.stereotype.Repository;

public interface TagRepository extends CRDRepository<Tag> {
    Tag findByName(String name);
}
