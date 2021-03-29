package com.epam.esm.persistence.repository;

import java.util.Optional;

public interface CRDRepository<T> {

    Long save(T t); //todo retutn object id

    Optional<T> findById(Long id);

    void delete(Long id);
}
