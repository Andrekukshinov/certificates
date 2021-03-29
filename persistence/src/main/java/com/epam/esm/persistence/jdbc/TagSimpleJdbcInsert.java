package com.epam.esm.persistence.jdbc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class TagSimpleJdbcInsert  extends SimpleJdbcInsert implements InitializingBean {
    private static final String TABLE_NAME = "tag";
    private static final String KEY = "id";

    public TagSimpleJdbcInsert(DataSource dataSource) {
        super(dataSource);
    }

    public TagSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void afterPropertiesSet() {
        this.withTableName(TABLE_NAME);
        this.setGeneratedKeyName(KEY);
    }

}
