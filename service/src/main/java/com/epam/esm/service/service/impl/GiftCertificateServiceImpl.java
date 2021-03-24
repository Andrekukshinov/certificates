package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateRepository certificateRepository;
    private final TagRepository tagRepository;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository certificateRepository, TagRepository tagRepository) {
        this.certificateRepository = certificateRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    @Override
    public void save(GiftCertificate certificate) throws PersistenceException {
        certificateRepository.save(certificate);
        Set<Tag> tags = certificate.getTags();
        if ((tags != null) && !tags.isEmpty()) {
            //findTagsByNames returns: Set<Tag>
            //compare with origin and ones that absent save and key id
            for (Tag tag: tags) {
                tagRepository.save(tag);
            }
            //certRepo save manyToMany
        }
    }

    //getCert
}
