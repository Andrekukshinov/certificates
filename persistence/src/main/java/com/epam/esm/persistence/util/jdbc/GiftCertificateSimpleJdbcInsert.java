package com.epam.esm.persistence.util.jdbc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class GiftCertificateSimpleJdbcInsert extends SimpleJdbcInsert implements InitializingBean {
    private static final String TABLE_NAME = "gift_certificates";
    private static final String KEY = "id";

    public GiftCertificateSimpleJdbcInsert(DataSource dataSource) {
        super(dataSource);
    }

    public GiftCertificateSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void afterPropertiesSet() {
        this.withTableName(TABLE_NAME);
        this.setGeneratedKeyName(KEY);
    }
}
