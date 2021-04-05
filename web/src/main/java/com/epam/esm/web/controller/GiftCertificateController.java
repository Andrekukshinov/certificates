package com.epam.esm.web.controller;

import com.epam.esm.service.dto.GiftCertificateTagDto;
import com.epam.esm.service.dto.GiftCertificatesNoTagDto;
import com.epam.esm.service.dto.SpecificationDto;
import com.epam.esm.service.exception.ValidationException;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftCertificateController {
    private static final String ID = "id";

    private final GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateTagDto> getGiftCertificateById(@PathVariable(ID) Long id) {
        return ResponseEntity.ok(certificateService.getCertificateWithTagsById(id));
    }

    @PostMapping
    public void saveGiftCertificate(@RequestBody GiftCertificateTagDto certificate) throws ValidationException {
        certificateService.save(certificate);
    }

    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable(ID) Long id) {
        certificateService.deleteCertificate(id);
    }

    @PutMapping
    public void updateCertificate(@RequestBody GiftCertificateTagDto certificateDto) throws ValidationException {
        certificateService.updateCertificate(certificateDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<GiftCertificatesNoTagDto>> getByParam(SpecificationDto specification) {
        List<GiftCertificatesNoTagDto> bySpecification = certificateService.getBySpecification(specification);
        return ResponseEntity.ok(bySpecification);
    }
}
