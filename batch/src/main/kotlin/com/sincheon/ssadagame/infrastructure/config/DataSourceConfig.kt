package com.sincheon.ssadagame.infrastructure.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.batch.BatchDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class ApiDataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.api")
    fun apiDataSourceProperties() = DataSourceProperties()

    @Bean
    @Primary
    fun apiDataSource() = apiDataSourceProperties().initializeDataSourceBuilder().build()!!

    @Bean
    fun apiJdbcTemplate(
        @Qualifier("apiDataSource")
        apiDataSource: DataSource
    ) = JdbcTemplate(apiDataSource())
}

@Configuration
class BatchDataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.batch")
    fun batchDataSourceProperties() = DataSourceProperties()

    @Bean
    @BatchDataSource
    fun batchDataSource() = batchDataSourceProperties().initializeDataSourceBuilder().build()!!

    @Bean
    fun batchJdbcTemplate(
        @Qualifier("batchDataSource")
        batchDataSource: DataSource
    ) = JdbcTemplate(batchDataSource())
}
