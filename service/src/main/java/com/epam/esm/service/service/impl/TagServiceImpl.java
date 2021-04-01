package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.service.TagGiftCertificateService;
import com.epam.esm.service.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagGiftCertificateService tagCertificateService;
    private final ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagGiftCertificateService tagCertificateService, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.tagCertificateService = tagCertificateService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTag(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void deleteTag(Long tagId) {
        tagCertificateService.deleteTags(tagId);
        tagRepository.delete(tagId);
    }

    @Override
    public Optional<TagDto> getTag(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(tag, TagDto.class));
    }
}
