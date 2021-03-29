package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.service.TagGiftCertificateService;
import com.epam.esm.service.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagGiftCertificateService tagCertificateService;

    public TagServiceImpl(TagRepository tagRepository, TagGiftCertificateService tagCertificateService) {
        this.tagRepository = tagRepository;
        this.tagCertificateService = tagCertificateService;
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void deleteTag(Long tagId) {
        tagCertificateService.deleteTags(tagId);
        tagRepository.delete(tagId);
    }

    @Override
    public Optional<Tag> getTag(Long id) {
        return tagRepository.findById(id);
    }
}
