package com.epam.esm.web.controller;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/gift")
public class GiftCertificateController {
    private GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping()
    public String get() throws ServiceException {
        return "certificateWithTagsById";
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificateById(@PathVariable("id") String stringId) throws ServiceException {
        Long id = Long.valueOf(stringId);
        GiftCertificate certificateWithTagsById = certificateService.getCertificateWithTagsById(id);
        return certificateWithTagsById;
    }
}
