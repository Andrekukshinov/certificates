package com.epam.esm.service.service;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.persistence.model.SearchSpecification;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

public interface GiftCertificateService {
    void save(GiftCertificate certificate) throws PersistenceException;

    GiftCertificate getCertificateWithTagsById (Long id) throws ServiceException;

    void deleteCertificate(Long certificateId);

    void updateCertificate(GiftCertificate certificate);

    List<GiftCertificate> getBySpecification(SearchSpecification searchSpecification);
}
