package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.exception.PersistenceException;

public interface CRDRepository<T> {
    void save(T t) throws PersistenceException; //todo retutn object id
    T getById(Long id);
    void delete(Long id);
}
