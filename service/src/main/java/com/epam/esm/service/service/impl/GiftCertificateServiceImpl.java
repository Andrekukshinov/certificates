package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private static final String WRONG_CERTIFICATE = "wrong certificate";
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
        Long certificateId = certificateRepository.save(certificate);
        Set<Tag> tags = certificate.getTags();
        if (tags != null && !tags.isEmpty()) {
            saveCertificateTags(certificateId, tags);
        }
    }

    @Override
    public GiftCertificate getCertificateWithTagsById(Long id) throws ServiceException {
        Optional<GiftCertificate> certificateOptional = certificateRepository.findById(id);
        GiftCertificate certificate = certificateOptional.orElseThrow(() -> new ServiceException(WRONG_CERTIFICATE));
        Set<Tag> certificateTags = tagRepository.findCertificateTags(id);//todo think of creating abstract repo with common methods
        certificate.setTags(certificateTags);
        return certificate;
    }

    private void saveCertificateTags(Long certificateId, Set<Tag> tags) throws PersistenceException {
        Set<Tag> foundTags = getFoundTags(tags);
        Set<Long> foundTagsIds = foundTags.stream().map(Tag::getId).collect(Collectors.toSet());
        List<Long> savedAbsentTagsIds = getSavedAbsentTagsIds(tags, foundTags);
        foundTagsIds.addAll(savedAbsentTagsIds);
        certificateRepository.saveGiftTags(certificateId, foundTagsIds);
    }

    private Set<Tag> getFoundTags(Set<Tag> tags) {
        Set<String> tagNames = tags.stream().map(Tag::getName).collect(Collectors.toSet());
        return tagRepository.findTagsByNames(tagNames);
    }

    private List<Long> getSavedAbsentTagsIds(Set<Tag> tags, Set<Tag> foundTags) throws PersistenceException {
        Set<Tag> absentTags = tags.stream().filter(foundTags::contains).collect(Collectors.toSet());
        List<Long> result = new ArrayList<>();
        for (Tag tag: absentTags) {
            result.add(tagRepository.save(tag));
        }
        return result;
    }

    //getCert
}
