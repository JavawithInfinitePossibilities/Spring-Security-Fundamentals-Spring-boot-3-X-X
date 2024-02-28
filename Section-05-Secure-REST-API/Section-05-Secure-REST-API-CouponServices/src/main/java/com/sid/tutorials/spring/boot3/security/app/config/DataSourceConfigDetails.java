package com.sid.tutorials.spring.boot3.security.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * @author kunmu On 28-12-2023
 */
@Configuration
public class DataSourceConfigDetails {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springCoreRepository() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.secondary.datasource")
    public DataSource springDataRepository() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTransactionManager platformTransactionManager() {
        return new JdbcTransactionManager(springCoreRepository());
    }
    @Bean(name = "transactionManager")
    @Primary
    public JdbcTransactionManager platformDataTransactionManager() {
        return new JdbcTransactionManager(springDataRepository());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
