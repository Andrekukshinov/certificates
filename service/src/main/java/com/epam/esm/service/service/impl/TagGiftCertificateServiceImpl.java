package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.repository.TagGiftCertificateRepository;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.service.TagGiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagGiftCertificateServiceImpl implements TagGiftCertificateService {

    private final TagRepository tagRepository;
    private final TagGiftCertificateRepository tagCertificateRepository;

    @Autowired
    public TagGiftCertificateServiceImpl(TagRepository tagRepository, TagGiftCertificateRepository tagCertificateRepository) {
        this.tagRepository = tagRepository;
        this.tagCertificateRepository = tagCertificateRepository;
    }

    @Override
    public void saveCertificateTags(Long certificateId, Set<Tag> certificateTags) {
        Set<Tag> foundTags = getFoundTags(certificateTags);
        Set<Long> foundTagsIds = foundTags.stream().map(Tag::getId).collect(Collectors.toSet());
        List<Long> savedAbsentTagsIds = getSavedAbsentTagsIds(certificateTags, foundTags);
        foundTagsIds.addAll(savedAbsentTagsIds);
        tagCertificateRepository.saveGiftCertificateTags(certificateId, foundTagsIds);
    }

    private Set<Tag> getFoundTags(Set<Tag> tags) {
        Set<String> tagNames = tags.stream().map(Tag::getName).collect(Collectors.toSet());
        return tagRepository.findTagsByNames(tagNames);
    }

    //todo change equals & hash method (include id)
    private List<Long> getSavedAbsentTagsIds(Set<Tag> tags, Set<Tag> foundTags) {
        Set<Tag> absentTags = tags.stream().filter(tag-> !foundTags.contains(tag)).collect(Collectors.toSet());
        List<Long> result = new ArrayList<>();
        for (Tag tag: absentTags) {
            result.add(tagRepository.save(tag));
        }
        return result;
    }
    @Override
    public void deleteCertificateTags(Long certificateId) {
        tagCertificateRepository.deleteCertificateTags(certificateId);
    }

    @Override
    public void deleteTags(Long tagId) {
        tagCertificateRepository.deleteTags(tagId);
    }

    @Override
    public Set<Tag> findCertificateTags(Long certificateId) {
        return tagCertificateRepository.findCertificateTags(certificateId);
    }
}
