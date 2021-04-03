package com.epam.esm.persistence.repository;

import java.util.Optional;

public interface CRDRepository<T> {

    Long save(T t);

    Optional<T> findById(Long id);

    int delete(Long id);
}
