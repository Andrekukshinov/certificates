package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.exception.PersistenceException;

import java.util.Optional;

public interface CRDRepository<T> {
    Long save(T t) throws PersistenceException; //todo retutn object id
    Optional<T> findById(Long id);
    void delete(Long id);
}
