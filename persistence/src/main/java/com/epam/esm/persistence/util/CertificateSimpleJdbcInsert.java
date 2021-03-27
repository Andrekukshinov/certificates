package com.epam.esm.persistence.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

public class CertificateSimpleJdbcInsert extends SimpleJdbcInsert implements InitializingBean {
    private static final String TABLE_NAME = "gift_certificate";
    private static final String KEY = "id";

    public CertificateSimpleJdbcInsert(DataSource dataSource) {
        super(dataSource);
    }


    public CertificateSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.withTableName(TABLE_NAME);
        this.setGeneratedKeyName(KEY);
    }
}
