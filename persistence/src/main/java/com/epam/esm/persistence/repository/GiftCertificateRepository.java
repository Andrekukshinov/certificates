package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.model.SearchSpecification;

import java.util.List;


public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    void update(GiftCertificate certificate);

    List<GiftCertificate> findBySpecification(SearchSpecification specification);
}
