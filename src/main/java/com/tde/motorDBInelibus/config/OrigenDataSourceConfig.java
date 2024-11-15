package com.tde.motorDBInelibus.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Primary;

@Configuration
public class OrigenDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.origen")
    public DataSourceProperties writingDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    public DataSource writingDataSource() {
        return writingDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
}