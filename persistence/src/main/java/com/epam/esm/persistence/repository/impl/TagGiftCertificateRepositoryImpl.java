package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.mapper.TagRowMapper;
import com.epam.esm.persistence.repository.TagGiftCertificateRepository;
import com.epam.esm.persistence.util.QueryCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class TagGiftCertificateRepositoryImpl implements TagGiftCertificateRepository {

    private static final String TAGS_GIFT_CERTIFICATES = "tags_gift_certificates";
    private static final String GIFT_CERTIFICATE_ID = "gift_certificate_id";
    private static final String TAG_ID = "tag_id";
    private static final String DELETE_CERTIFICATE_TAGS = " DELETE FROM tags_gift_certificates" +
            " WHERE tags_gift_certificates.gift_certificate_id = ?";
    private static final String GET_CERTIFICATE_TAGS =
            " SELECT T.id as id, name " +
                    " FROM tag AS T" +
                    " INNER JOIN tags_gift_certificates AS TGC ON T.id = TGC.tag_id" +
                    " WHERE TGC.gift_certificate_id =?";
    private static final String DELETE_TAGS = " DELETE FROM tags_gift_certificates" +
            " WHERE tags_gift_certificates.tag_id = ?";
    private final JdbcTemplate jdbc;
    private final QueryCreator queryCreator;
    private final TagRowMapper tagRowMapper;


    @Autowired
    public TagGiftCertificateRepositoryImpl(JdbcTemplate jdbc, QueryCreator queryCreator, TagRowMapper tagRowMapper) {
        this.jdbc = jdbc;
        this.queryCreator = queryCreator;
        this.tagRowMapper = tagRowMapper;
    }

    @Override
    @Transactional
    public void saveGiftCertificateTags(Long certificateId, Set<Long> tagsIds) {
        Set<String> fieldNames = new LinkedHashSet<>();
        fieldNames.add(GIFT_CERTIFICATE_ID);
        fieldNames.add(TAG_ID);
        tagsIds.forEach(tagId -> {
            String saveQuery = queryCreator.getSaveQuery(TAGS_GIFT_CERTIFICATES, fieldNames);
            jdbc.update(saveQuery, certificateId, tagId);
        });
    }

    @Override
    public void deleteCertificateTags(Long id) {
        jdbc.update(DELETE_CERTIFICATE_TAGS, id);
    }

    @Override
    public Set<Tag> findCertificateTags(Long certificateId) {
        return new HashSet<>(jdbc.query(GET_CERTIFICATE_TAGS, tagRowMapper, certificateId));
    }

    @Override
    public void deleteTags(Long tagId) {
        jdbc.update(DELETE_TAGS, tagId);
    }
}
