package com.epam.esm.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages={"com.epam.esm.persistence"})
@Profile("test")
public class TestConfiguration {
    @Bean
    @Profile("test")
    public DataSource getTestDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:sql/schema.sql")
                .addScript("classpath:sql/data.sql")
                .build();
    }

    @Bean
    @Profile("test")
    public JdbcTemplate h2JdbcTemplate(DataSource source) {
        return new JdbcTemplate(source);
    }

}
