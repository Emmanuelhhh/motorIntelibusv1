package com.tde.motorDBInelibus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.sql.DataSource;

@Configuration
public class DestinoDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.destino")
    public DataSourceProperties writingDataSourceProperties2() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource writingDataSource2() {
        return writingDataSourceProperties2()
          .initializeDataSourceBuilder()
          .build();
    }
}