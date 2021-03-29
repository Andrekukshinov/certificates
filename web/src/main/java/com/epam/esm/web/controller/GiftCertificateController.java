package com.epam.esm.web.controller;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gifts")
public class GiftCertificateController {
    private static final String ID = "id";

    private final GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificateById(@PathVariable(ID) Long id) throws ServiceException {
        return certificateService.getCertificateWithTagsById(id);
    }

    @PostMapping
    public void saveGiftCertificate(@RequestBody GiftCertificate certificate) throws PersistenceException {
        certificateService.save(certificate);
    }

    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable(ID) Long id) {
        certificateService.deleteCertificate(id);
    }

    @PutMapping
    public void updateCertificate(@RequestBody GiftCertificate certificate) {
        certificateService.updateCertificate(certificate);
    }
}
//todo read about ways of creating urls
