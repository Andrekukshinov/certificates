package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.exception.PersistenceException;
import com.epam.esm.persistence.extractor.FieldsExtractor;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.persistence.util.QueryCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;


@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    private static final String TABLE_NAME = "gift_certificate";
    private static final String ENTITY_ALREADY_EXISTS = "entity already exists!";

    private final JdbcTemplate jdbc;
    private final FieldsExtractor<GiftCertificate> extractor;
    private final QueryCreator queryCreator;

    @Autowired
    public GiftCertificateRepositoryImpl(JdbcTemplate jdbc, FieldsExtractor<GiftCertificate> extractor, QueryCreator queryCreator) {
        this.jdbc = jdbc;
        this.extractor = extractor;
        this.queryCreator = queryCreator;
    }

    @Override
    public void save(GiftCertificate certificate) throws PersistenceException {
        if (certificate.getId() != null) {
            throw new PersistenceException(ENTITY_ALREADY_EXISTS);
        }
        Map<String, Object> fieldsValuesMap = extractor.getFieldsValuesMap(certificate);
        String saveQuery = queryCreator.getSaveQuery(TABLE_NAME, fieldsValuesMap);
        Collection<Object> values = fieldsValuesMap.values();
        Object[] valuesToBeInserted = values.toArray();
        jdbc.update(saveQuery, valuesToBeInserted);
        //todo return saved id
    }

    @Override
    public GiftCertificate getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(GiftCertificate certificate) {

    }
}
