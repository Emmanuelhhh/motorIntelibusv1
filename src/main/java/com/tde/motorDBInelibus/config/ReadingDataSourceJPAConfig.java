package com.tde.motorDBInelibus.config;

import java.util.Collections;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.tde.motorDBInelibus.persistence.origen",
        entityManagerFactoryRef = "readingEntityManagerFactory",
        transactionManagerRef = "readingTransactionManager"
)
public class ReadingDataSourceJPAConfig {
	
	 // Configuración de DataSource para lectura
    @Bean(name = "origenDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.origen")
    public DataSource OrigenDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readingEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean readingEntityManagerFactory(
            @Qualifier("origenDataSource") DataSource origenDataSource, 
            EntityManagerFactoryBuilder builder) {
        return builder
        		.dataSource(origenDataSource)
                .packages("com.tde.motorDBInelibus.persistence.origen")
              //  .properties(Collections.singletonMap("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect"))
                .build();
    }

    @Bean(name = "readingTransactionManager")
    @Primary
    public PlatformTransactionManager readingTransactionManager(
            @Qualifier("readingEntityManagerFactory") LocalContainerEntityManagerFactoryBean readingEntityManagerFactory) {
        return new JpaTransactionManager(readingEntityManagerFactory.getObject());
    }
}

/*
@Configurable
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.tde.motorDBInelibus.persistence.repository.origen",
        entityManagerFactoryRef = "readingEntityManagerFactory",
        transactionManagerRef = "readingTransactionManager"
)
public class ReadingDataSourceJPAConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean readingEntityManagerFactory(
      @Qualifier("origenDataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages("com.tde.motorDBInelibus.persistence.entity.origen")
          .build();
    }

    @Bean
    public PlatformTransactionManager readingTransactionManager(
      @Qualifier("readingEntityManagerFactory") LocalContainerEntityManagerFactoryBean readingEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(readingEntityManagerFactory.getObject()));
    }
}

*/


