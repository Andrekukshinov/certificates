package com.epam.esm.service.service;

import com.epam.esm.service.dto.GiftCertificateTagDto;
import com.epam.esm.service.dto.GiftCertificatesNoTagDto;
import com.epam.esm.service.dto.SpecificationDto;
import com.epam.esm.service.exception.ValidationException;

import java.util.List;

public interface GiftCertificateService {
    void save(GiftCertificateTagDto certificate) throws ValidationException;

    GiftCertificateTagDto getCertificateWithTagsById(Long id);

    void deleteCertificate(Long certificateId);

    void updateCertificate(GiftCertificateTagDto certificateDto) throws ValidationException;

    List<GiftCertificatesNoTagDto> getBySpecification(SpecificationDto searchSpecification);
}
