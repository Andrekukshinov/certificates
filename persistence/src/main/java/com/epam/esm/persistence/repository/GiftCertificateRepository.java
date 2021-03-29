package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.GiftCertificate;


public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    void update(GiftCertificate certificate);
}
