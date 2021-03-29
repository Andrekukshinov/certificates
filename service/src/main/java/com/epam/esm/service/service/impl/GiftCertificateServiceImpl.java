package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.service.GiftCertificateService;
import com.epam.esm.service.service.TagGiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private static final String WRONG_CERTIFICATE = "wrong certificate";

    private final GiftCertificateRepository certificateRepository;
    private final TagGiftCertificateService tagCertificateService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository certificateRepository, TagGiftCertificateService tagCertificateService) {
        this.certificateRepository = certificateRepository;
        this.tagCertificateService = tagCertificateService;
    }

    @Transactional
    @Override
    public void save(GiftCertificate certificate) {
        Long certificateId = certificateRepository.save(certificate);
        Set<Tag> tags = certificate.getTags();
        if (tags != null && !tags.isEmpty()) {
            tagCertificateService.saveCertificateTags(certificateId, tags);
        }
    }

    @Override
    public GiftCertificate getCertificateWithTagsById(Long id) throws ServiceException {
        Optional<GiftCertificate> certificateOptional = certificateRepository.findById(id);
        GiftCertificate certificate = certificateOptional.orElseThrow(() -> new ServiceException(WRONG_CERTIFICATE));
        Set<Tag> certificateTags = tagCertificateService.findCertificateTags(id);//todo think of creating abstract repo with common methods
        certificate.setTags(certificateTags);
        return certificate;
    }

    @Transactional
    @Override
    public void deleteCertificate(Long id) {
        tagCertificateService.deleteCertificateTags(id);
        certificateRepository.delete(id);
    }

    @Override
    @Transactional
    public void updateCertificate(GiftCertificate certificate) {
        certificateRepository.update(certificate);
        Set<Tag> tags = certificate.getTags();
        Long certificateId = certificate.getId();
        if (tags != null && tags.isEmpty()) {
            tagCertificateService.saveCertificateTags(certificateId, tags);
        } else if (tags != null) {
            tagCertificateService.deleteCertificateTags(certificateId);
        }
    }

    //getCert
}
