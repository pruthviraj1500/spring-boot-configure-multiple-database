package com.programming.configure_multiple_databases.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "bookEntityManagerFactory",
                       transactionManagerRef = "bookTransactionManager",
                       basePackages = {"com.programming.configure_multiple_databases.book.repository"})
public class BookDBConfig {

    @Bean(name = "bookDataSource")
    @ConfigurationProperties(prefix = "spring.book.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "bookEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("bookDataSource") DataSource dataSource){

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("com.programming.configure_multiple_databases.model.book")
                .persistenceUnit("Book")
                .build();

    }

    @Bean(name = "bookTransactionManager")
    public PlatformTransactionManager bookTransactionManager(
                  @Qualifier("bookEntityManagerFactory") EntityManagerFactory bookEntityManagerFactory){

        return new JpaTransactionManager(bookEntityManagerFactory);

    }

}
