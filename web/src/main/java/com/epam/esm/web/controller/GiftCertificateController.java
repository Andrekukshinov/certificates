package com.epam.esm.web.controller;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gift")
public class GiftCertificateController {
    private GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificateById(@PathVariable("id") Long id) throws ServiceException {
        return certificateService.getCertificateWithTagsById(id);
    }

    @PostMapping
    public void saveGiftCertificate(@RequestBody GiftCertificate certificate) throws PersistenceException {
        certificateService.save(certificate);
    }
}
