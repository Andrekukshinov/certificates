package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.GiftCertificate;

import java.util.Set;


public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    void update(GiftCertificate certificate);

    void saveGiftTags(Long certificateId, Set<Long> tagsIds);

    void deleteCertificateTags(Long id);
}
