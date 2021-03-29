package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.Tag;

import java.util.Set;

public interface TagGiftCertificateRepository {
    void saveGiftCertificateTags(Long certificateId, Set<Long> tagsIds);

    void deleteCertificateTags(Long certificateId);

    Set<Tag> findCertificateTags(Long certificateId);

    void deleteTags(Long tagId);
}
