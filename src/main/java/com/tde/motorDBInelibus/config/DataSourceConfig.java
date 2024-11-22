package com.tde.motorDBInelibus.config;

import javax.sql.DataSource;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    // Configuración de DataSource para lectura
    @Bean(name = "OrigenDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.origen")
    public DataSource OrigenDataSource() {
        return DataSourceBuilder.create().build();
    }

    // Configuración de DataSource para escritura
    @Bean(name = "DestinoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.destino")
    public DataSource DestinoDataSource() {
        return DataSourceBuilder.create().build();
    }
}
*/
