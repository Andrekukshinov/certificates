package com.epam.esm.service.service;

import com.epam.esm.persistence.entity.Tag;

import java.util.Set;

public interface TagGiftCertificateService {

    void saveCertificateTags(Long certificateId, Set<Tag> certificateTags);

    void deleteCertificateTags(Long certificateId);

    void deleteTags(Long tagId);

    Set<Tag> findCertificateTags(Long certificateId);
}
