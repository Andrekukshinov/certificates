package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.mapper.TagRowMapper;
import com.epam.esm.persistence.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//plain and stupid
@Repository
public class TagRepositoryImpl implements TagRepository {
    private static final String SAVE_TAG = "INSERT INTO tag(name) VALUES (?)";
    private static final String GET_BY_ID = "SELECT * FROM tag WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT * FROM tag WHERE name = ?";
    private static final String DELETE_TAG = "DELETE FROM tag WHERE id = ?";

    private final JdbcTemplate jdbc;
    private final TagRowMapper mapper;

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbc, TagRowMapper mapper) {
        this.jdbc = jdbc;
        this.mapper = mapper;
    }

    @Override
    public void save(Tag tag) {
        Tag persistenceTag = findByName(tag.getName());
        if (persistenceTag != null) {

        }
        jdbc.update(SAVE_TAG, tag.getName());
    }

    @Override
    public Tag getById(Long id) {
        return jdbc.queryForObject(GET_BY_ID, mapper, id);
    }

    @Override
    public void delete(Long id) {
        jdbc.update(DELETE_TAG, id);
    }

    @Override
    public Tag findByName(String name) {
        return jdbc.queryForObject(GET_BY_NAME, mapper, name);
    }
}
