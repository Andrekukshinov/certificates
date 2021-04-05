package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityNotFoundException;
import com.epam.esm.service.exception.ValidationException;
import com.epam.esm.service.service.TagGiftCertificateService;
import com.epam.esm.service.service.TagService;
import com.epam.esm.service.validators.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private static final String WRONG_TAG = "tag with id = %d not found";

    private final TagRepository tagRepository;
    private final TagGiftCertificateService tagCertificateService;
    private final ModelMapper modelMapper;
    private final Validator<TagDto> validator;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagGiftCertificateService tagCertificateService, ModelMapper modelMapper, Validator<TagDto> validator) {
        this.tagRepository = tagRepository;
        this.tagCertificateService = tagCertificateService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void saveTag(TagDto tagDto) throws ValidationException {
        validator.validate(tagDto);
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
    public TagDto getTag(Long id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        Tag tag = tagOptional.orElseThrow(() -> new EntityNotFoundException(String.format(WRONG_TAG, id)));
        return modelMapper.map(tag, TagDto.class);
    }
}
