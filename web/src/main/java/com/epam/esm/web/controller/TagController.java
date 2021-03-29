package com.epam.esm.web.controller;


import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.service.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public void saveTag(Tag tag) {
        tagService.saveTag(tag);
    }

    @GetMapping("{id}")
    public Tag getTagById(@PathVariable Long id) {
        Optional<Tag> tag = tagService.getTag(id);
        return tag.orElseThrow(() -> new RuntimeException("changeMe"));//todo change me
    }

    @DeleteMapping("{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}
