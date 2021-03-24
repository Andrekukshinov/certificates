package com.epam.esm.service.service;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.exception.PersistenceException;
import org.springframework.stereotype.Service;


public interface GiftCertificateService {
    void save(GiftCertificate certificate) throws PersistenceException;
}
