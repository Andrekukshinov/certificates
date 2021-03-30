package com.epam.esm.web.controller;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.service.service.TagGiftCertificateService;
import com.epam.esm.service.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// TODO: 30.03.2021 response entity
@RestController
@RequestMapping("api/tags")
public class TagController {

    private final TagService tagService;
    private final TagGiftCertificateService certificateService;

    public TagController(TagService tagService, TagGiftCertificateService certificateService) {
        this.tagService = tagService;
        this.certificateService = certificateService;
    }

    @PostMapping
    public void saveTag(Tag tag) {
        tagService.saveTag(tag);
    }

    @GetMapping("{id}")
    public Tag getTagById(@PathVariable Long id) {
        Optional<Tag> tag = tagService.getTag(id);
        return tag.orElseThrow(() -> new RuntimeException("changeMe"));//fixme
    }

    @DeleteMapping("{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }

}
