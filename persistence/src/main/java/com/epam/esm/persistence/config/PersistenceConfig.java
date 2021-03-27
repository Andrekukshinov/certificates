package com.epam.esm.persistence.config;

import com.epam.esm.persistence.util.CertificateSimpleJdbcInsert;
import com.epam.esm.persistence.util.TagSimpleJdbcInsert;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "com.epam.esm.persistence")
@EnableTransactionManagement
@PropertySource(value = "classpath:/connectionPool.properties")
public class PersistenceConfig {

    //    private static final String DRIVER_MYSQL = "driver.mysql";
//    private static final String URL = "url";
//    private static final String PASSWORD = "password";
//    private static final String USERNAME = "username";
//    private static final String CONNECTIONS_MIN = "connections.min";
//    private static final String CLOSE_METHOD = "close";
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/gift_certificates_management";
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";
    private static final int MAX_CONNECTIONS = 8;

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource getMySqlDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(MYSQL_DRIVER);
        dataSource.setJdbcUrl(URL);
        dataSource.setPassword(PASSWORD);
        dataSource.setUser(USERNAME);
        dataSource.setMinPoolSize(MAX_CONNECTIONS);
        return dataSource;
    }
//    @Bean(destroyMethod = CLOSE_METHOD)
//    public ComboPooledDataSource getMysqlDataSource(Environment environment) throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass(environment.getProperty(DRIVER_MYSQL));
//        dataSource.setJdbcUrl(environment.getProperty(URL));
//        dataSource.setPassword(environment.getProperty(PASSWORD));
//        dataSource.setUser(environment.getProperty(USERNAME));
//        String minConnectionsString = environment.getProperty(CONNECTIONS_MIN);
//        dataSource.setMinPoolSize(Integer.parseInt(minConnectionsString));
//        return dataSource;
//    }

    @Bean
    public JdbcTemplate mySqlJdbcTemplate(DataSource source) {
        return new JdbcTemplate(source);
    }

    @Bean
    public TagSimpleJdbcInsert tagSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new TagSimpleJdbcInsert(jdbcTemplate);
    }

    @Bean
    public CertificateSimpleJdbcInsert certificateSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new CertificateSimpleJdbcInsert(jdbcTemplate);
    }

    @Bean
    public DataSourceTransactionManager getTransactionManager(DataSource source) {
        return new DataSourceTransactionManager(source);
    }
}
// TODO: 24.03.2021 impl mappers, query creators, field extractors
