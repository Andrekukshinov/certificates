package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.extractor.FieldsExtractor;
import com.epam.esm.persistence.extractor.SpecificationExtractor;
import com.epam.esm.persistence.model.SearchSpecification;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.persistence.util.creator.QueryCreator;
import com.epam.esm.persistence.util.jdbc.GiftCertificateSimpleJdbcInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    private static final String TABLE_NAME = "gift_certificates";
    private static final String ENTITY_ALREADY_EXISTS = "entity already exists!";
    private static final String WHERE_ID = " WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM " + TABLE_NAME + WHERE_ID;
    private static final String DELETE_CERTIFICATE = " DELETE FROM gift_certificates WHERE id = ?";
    private static final String ID = "id";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert jdbcInsert;
    private final FieldsExtractor<GiftCertificate> certificateExtractor;
    private final QueryCreator queryCreator;
    private final RowMapper<GiftCertificate> mapper;
    private final SpecificationExtractor specificationExtractor;

    @Autowired
    public GiftCertificateRepositoryImpl(
            JdbcTemplate jdbc,
            GiftCertificateSimpleJdbcInsert jdbcInsert,
            FieldsExtractor<GiftCertificate> certificateExtractor,
            QueryCreator queryCreator, RowMapper<GiftCertificate> mapper,
            SpecificationExtractor specificationExtractor) {
        this.jdbc = jdbc;
        this.jdbcInsert = jdbcInsert;
        this.certificateExtractor = certificateExtractor;
        this.queryCreator = queryCreator;
        this.mapper = mapper;
        this.specificationExtractor = specificationExtractor;
    }

    @Override
    public Long save(GiftCertificate certificate) {
        Map<String, Object> fieldsValuesMap = certificateExtractor.getFieldsValuesMap(certificate);
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
        jdbc.update(DELETE_CERTIFICATE, id);
    }

    @Override
    public void update(GiftCertificate certificate) {
        Map<String, Object> fieldsValuesMap = certificateExtractor.getFieldsValuesMap(certificate);
        Object updateParam = fieldsValuesMap.get(ID);
        String updateQuery = queryCreator.getUpdateQuery(TABLE_NAME, fieldsValuesMap.keySet(), ID);
        List<Object> objects = new ArrayList<>(fieldsValuesMap.values());
        objects.add(updateParam);
        jdbc.update(updateQuery, objects.toArray());
    }

    @Override
    public List<GiftCertificate> findBySpecification(SearchSpecification specification) {
        String qry = queryCreator.getFindCertificateByCondition(specification);
        List<Object> values = specificationExtractor.getValues(specification);
        return jdbc.query(qry, mapper, values);
    }

}
