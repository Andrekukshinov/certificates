package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.GiftCertificate;
import org.springframework.stereotype.Repository;


public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    void update(GiftCertificate certificate);

//    void saveCertificateTags(GiftCertificate certificate);
}
