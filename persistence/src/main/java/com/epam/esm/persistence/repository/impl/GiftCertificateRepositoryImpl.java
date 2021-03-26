package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.persistence.extractor.FieldsExtractor;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.persistence.util.QueryCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Types;
import java.util.*;


@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    private static final String TABLE_NAME = "gift_certificate";
    private static final String ENTITY_ALREADY_EXISTS = "entity already exists!";
    private static final String TAGS_GIFT_CERTIFICATES = "tags_gift_certificates";
    private static final String GIFT_CERTIFICATE_ID = "gift_certificate_id";
    private static final String TAG_ID = "tag_id";
    private static final String WHERE_ID = " WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM " + TABLE_NAME + WHERE_ID;

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert jdbcInsert;
    private final FieldsExtractor<GiftCertificate> certificateExtractor;
    private final QueryCreator queryCreator;
    private final RowMapper<GiftCertificate> mapper;


    @Autowired
    public GiftCertificateRepositoryImpl(
            JdbcTemplate jdbc,
            @Qualifier("jdbcInsertGift") SimpleJdbcInsert jdbcInsert,
            FieldsExtractor<GiftCertificate> certificateExtractor,
            QueryCreator queryCreator, RowMapper<GiftCertificate> mapper) {
        this.jdbc = jdbc;
        this.jdbcInsert = jdbcInsert;
        this.certificateExtractor = certificateExtractor;
        this.queryCreator = queryCreator;
        this.mapper = mapper;
    }

    @Override
    public Long save(GiftCertificate certificate) throws PersistenceException {
        Map<String, Object> fieldsValuesMap = certificateExtractor.getFieldsValuesMap(certificate);
        Set<String> strings = fieldsValuesMap.keySet();
        jdbcInsert.setColumnNames(new ArrayList<>(strings));
        Number number = jdbcInsert.executeAndReturnKey(fieldsValuesMap);
        return number.longValue();
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        GiftCertificate certificates = jdbc.queryForObject(GET_BY_ID, mapper, id);
        return Optional.ofNullable(certificates);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(GiftCertificate certificate) {

    }

    @Override
    public void saveGiftTags(Long certificateId, Set<Long> tagsIds) {
        Set<String> fieldNames = new LinkedHashSet<>();
        fieldNames.add(GIFT_CERTIFICATE_ID);
        fieldNames.add(TAG_ID);
        tagsIds.forEach(tagId -> {
            String saveQuery = queryCreator.getSaveQuery(TAGS_GIFT_CERTIFICATES, fieldNames);
            jdbc.update(saveQuery, certificateId, tagId);
        });
    }
}
