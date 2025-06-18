package com.tde.motorDBInelibus.config;



import javax.sql.DataSource;


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

@Configuration
@EnableJpaRepositories(
        basePackages = "com.tde.motorDBInelibus.persistence.origencard",
        entityManagerFactoryRef = "readingCardEntityManagerFactory",
        transactionManagerRef = "readingCardTransactionManager"
)
public class ReadingCardDataSourceJPAConfig {
	
	 // Configuración de DataSource para lectura
    @Bean(name = "origenCardDataSource")

    @ConfigurationProperties(prefix = "spring.datasource.origencard")
    public DataSource OrigenCardDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readingCardEntityManagerFactory")
    
    public LocalContainerEntityManagerFactoryBean readingCardEntityManagerFactory(
            @Qualifier("origenCardDataSource") DataSource origenCardDataSource, 
            EntityManagerFactoryBuilder builder) {
        return builder
        		.dataSource(origenCardDataSource)
                .packages("com.tde.motorDBInelibus.persistence.origencard")
              //  .properties(Collections.singletonMap("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect"))
                .build();
    }

    @Bean(name = "readingCardTransactionManager")
   
    public PlatformTransactionManager readingCardTransactionManager(
            @Qualifier("readingCardEntityManagerFactory") LocalContainerEntityManagerFactoryBean readingCardEntityManagerFactory) {
        return new JpaTransactionManager(readingCardEntityManagerFactory.getObject());
    }
}
