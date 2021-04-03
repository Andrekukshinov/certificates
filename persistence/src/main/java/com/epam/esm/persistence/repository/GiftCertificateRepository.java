package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.model.SearchSpecification;
import com.epam.esm.persistence.model.SortSpecification;

import java.util.List;


public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    int update(GiftCertificate certificate);

    List<GiftCertificate> findBySpecification(SearchSpecification searchSpecification, SortSpecification sortSpecification);
}
