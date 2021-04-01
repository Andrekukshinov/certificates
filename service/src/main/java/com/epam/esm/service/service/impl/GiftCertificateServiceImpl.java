package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.model.SearchSpecification;
import com.epam.esm.persistence.model.SortSpecification;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.service.dto.*;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.service.GiftCertificateService;
import com.epam.esm.service.service.TagGiftCertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private static final String WRONG_CERTIFICATE = "wrong certificate";

    private final GiftCertificateRepository certificateRepository;
    private final TagGiftCertificateService tagCertificateService;
    private final ModelMapper modelMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository certificateRepository, TagGiftCertificateService tagCertificateService, ModelMapper modelMapper) {
        this.certificateRepository = certificateRepository;
        this.tagCertificateService = tagCertificateService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void save(GiftCertificateTagRequestDto certificateDto) {
        GiftCertificate certificate = modelMapper.map(certificateDto, GiftCertificate.class);
        certificate.setCreateDate(LocalDate.now());
        certificate.setLastUpdateDate(LocalDate.now());
        Long certificateId = certificateRepository.save(certificate);
        Set<Tag> tags = certificate.getTags();
        if (tags != null && !tags.isEmpty()) {
            tagCertificateService.saveCertificateTags(certificateId, tags);
        }
    }

    @Override
    public GiftCertificateTagDto getCertificateWithTagsById(Long id) throws ServiceException {
        Optional<GiftCertificate> certificateOptional = certificateRepository.findById(id);
        GiftCertificate certificate = certificateOptional.orElseThrow(() -> new ServiceException(WRONG_CERTIFICATE));
        Set<TagDto> certificateTagsDto = tagCertificateService.findCertificateTags(id);
        Set<Tag> tags = certificateTagsDto.stream()
                .map(tagDto -> modelMapper.map(tagDto, Tag.class))
                .collect(Collectors.toSet());
        certificate.setTags(tags);
        return modelMapper.map(certificate, GiftCertificateTagDto.class);
    }
    //map struct

    @Transactional
    @Override
    public void deleteCertificate(Long id) {
        tagCertificateService.deleteCertificateTags(id);
        certificateRepository.delete(id);
    }

    @Override
    @Transactional
    public void updateCertificate(GiftCertificateTagRequestDto certificateDto) {
        GiftCertificate certificate = modelMapper.map(certificateDto, GiftCertificate.class);
        certificate.setLastUpdateDate(LocalDate.now());
        certificateRepository.update(certificate);
        Set<Tag> tags = certificate.getTags();
        Long certificateId = certificate.getId();
        if (tags != null && tags.isEmpty()) {
            tagCertificateService.saveCertificateTags(certificateId, tags);
        } else if (tags != null) {
            tagCertificateService.deleteCertificateTags(certificateId);
        }
    }

    @Override
    public List<GiftCertificatesNoTagDto> getBySpecification(SpecificationDto specificationDto) {
        SearchSpecification searchSpecification = modelMapper.map(specificationDto, SearchSpecification.class);
        SortSpecification sortSpecification = modelMapper.map(specificationDto, SortSpecification.class);
        List<GiftCertificate> certificates = certificateRepository.findBySpecification(searchSpecification, sortSpecification);
        return certificates.stream()
                .map(certificate -> modelMapper.map(certificate, GiftCertificatesNoTagDto.class))
                .collect(Collectors.toList());
    }

}
