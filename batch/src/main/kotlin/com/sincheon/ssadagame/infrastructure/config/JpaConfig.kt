package com.sincheon.ssadagame.infrastructure.config

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.sincheon.ssadagame.domain"],
    entityManagerFactoryRef = "apiEntityManagerFactory",
    transactionManagerRef = "apiTransactionManager",
)
class JpaConfig {
    @Bean
    fun apiEntityManagerFactory(
        @Qualifier("apiDataSource")
        apiDataSource: DataSource,
        builder: EntityManagerFactoryBuilder,
    ) = builder
        .dataSource(apiDataSource)
        .packages("com.sincheon.ssadagame.domain")
        .properties(mapOf("hibernate.physical_naming_strategy" to CamelCaseToUnderscoresNamingStrategy::class.java.name))
        .build()!!

    @Bean
    fun apiTransactionManager(
        @Qualifier("apiEntityManagerFactory")
        apiEntityManagerFactory: EntityManagerFactory,
    ) = JpaTransactionManager(apiEntityManagerFactory)
}
