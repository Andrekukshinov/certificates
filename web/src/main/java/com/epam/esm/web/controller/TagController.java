package com.epam.esm.web.controller;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;

    }

    @PostMapping
    public void saveTag(TagDto tag) {
        tagService.saveTag(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id) {
        Optional<TagDto> tag = tagService.getTag(id);
        return ResponseEntity.ok(tag.orElseThrow(() -> new RuntimeException("changeMe")));//fixme
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }

}
