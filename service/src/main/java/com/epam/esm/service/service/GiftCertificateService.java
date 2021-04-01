package com.epam.esm.service.service;

import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.service.dto.GiftCertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateTagRequestDto;
import com.epam.esm.service.dto.GiftCertificatesNoTagDto;
import com.epam.esm.service.dto.SpecificationDto;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

public interface GiftCertificateService {
    void save(GiftCertificateTagRequestDto certificate) throws PersistenceException;

    GiftCertificateTagDto getCertificateWithTagsById (Long id) throws ServiceException;

    void deleteCertificate(Long certificateId);

    void updateCertificate(GiftCertificateTagRequestDto certificateDto);

    List<GiftCertificatesNoTagDto> getBySpecification(SpecificationDto searchSpecification);
}
