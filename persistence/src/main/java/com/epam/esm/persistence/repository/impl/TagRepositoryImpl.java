package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.mapper.TagRowMapper;
import com.epam.esm.persistence.repository.TagRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//plain and stupid
@Repository
public class TagRepositoryImpl implements TagRepository {
    private static final String SAVE_TAG = "INSERT INTO tag(name) VALUES (?)";
    private static final String GET_BY_ID = "SELECT * FROM tag WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT * FROM tag WHERE name = ?";
    private static final String DELETE_TAG = "DELETE FROM tag WHERE id = ?";
    private static final String QUESTION = "?";
    private static final String COMMA = ", ";
    private static final String GET_ALL_WHERE_NAME_IN = "SELECT * FROM tag WHERE name IN (%s)";
    private static final String GET_CERTIFICATE_TAGS =
            " SELECT T.id as id, name " +
            " FROM tag AS T" +
            " INNER JOIN tags_gift_certificates AS TGC ON T.id = TGC.tag_id" +
            " WHERE TGC.gift_certificate_id =?";

    private final JdbcTemplate jdbc;
    private final TagRowMapper mapper;

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbc, TagRowMapper mapper) {
        this.jdbc = jdbc;
        this.mapper = mapper;
    }

    @Override
    public Long save(Tag tag) {
        PreparedStatementCreatorFactory statementFactory = new PreparedStatementCreatorFactory(SAVE_TAG);
        statementFactory.setReturnGeneratedKeys(true);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = statementFactory.newPreparedStatementCreator(List.of(tag.getName()));
        return Long.valueOf(jdbc.update(psc, keyHolder));
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
        return Sets.newHashSet(jdbc.query(query, mapper));
    }

    @Override
    public Set<Tag> findCertificateTags(Long certificateId) {
        return Sets.newHashSet(jdbc.query(GET_CERTIFICATE_TAGS, mapper, certificateId));
    }
}
