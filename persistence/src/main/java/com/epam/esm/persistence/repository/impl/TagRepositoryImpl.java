package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.extractor.FieldsExtractor;
import com.epam.esm.persistence.mapper.TagRowMapper;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.persistence.util.TagSimpleJdbcInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private static final String GET_BY_ID = "SELECT * FROM tag WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT * FROM tag WHERE name = ?";
    private static final String DELETE_TAG = "DELETE FROM tag WHERE id = ?";
    private static final String QUESTION = "?";
    private static final String COMMA = ", ";
    private static final String GET_ALL_WHERE_NAME_IN = "SELECT * FROM tag WHERE name IN (%s)";


    private final JdbcTemplate jdbc;
    private final TagRowMapper mapper;
    private final FieldsExtractor<Tag> tagFieldsExtractor;
        private final SimpleJdbcInsert jdbcInsert;


    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbc, TagRowMapper mapper,
                             FieldsExtractor<Tag> tagFieldsExtractor,
                             TagSimpleJdbcInsert jdbcInsert) {
        this.jdbc = jdbc;
        this.mapper = mapper;
        this.tagFieldsExtractor = tagFieldsExtractor;
        this.jdbcInsert = jdbcInsert;
    }

    @Override
    public Long save(Tag tag) {
        Map<String, Object> fieldsValuesMap = tagFieldsExtractor.getFieldsValuesMap(tag);
        Number number = jdbcInsert.executeAndReturnKey(fieldsValuesMap);
        return number.longValue();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        Tag tag = jdbc.queryForObject(GET_BY_ID, mapper, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public void delete(Long id) {
        jdbc.update(DELETE_TAG, id);
    }

    @Override
    public Tag findByName(String name) {
        return jdbc.queryForObject(GET_BY_NAME, mapper, name);
    }

    @Override
    public Set<Tag> findTagsByNames(Set<String> tagNames) {
        String sqlNamesPlaceHolder = String.join(COMMA, Collections.nCopies(tagNames.size(), QUESTION));
        String query = String.format(GET_ALL_WHERE_NAME_IN, sqlNamesPlaceHolder);
        return Set.copyOf(jdbc.query(query, mapper, tagNames.toArray()));
    }
}
