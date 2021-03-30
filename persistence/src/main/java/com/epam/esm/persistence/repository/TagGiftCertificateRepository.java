package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.model.TagGiftCertificateId;

import java.util.List;
import java.util.Set;

public interface TagGiftCertificateRepository {
    void saveGiftCertificateTags(List<TagGiftCertificateId> tagGiftCertificateIds);

    void deleteCertificateTags(Long certificateId);

    Set<Tag> findCertificateTags(Long certificateId);

    void deleteTags(Long tagId);

}
