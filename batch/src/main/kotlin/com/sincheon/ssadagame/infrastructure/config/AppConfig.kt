package com.sincheon.ssadagame.infrastructure.config

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableBatchProcessing
@EnableScheduling
@ConfigurationPropertiesScan("com.sincheon.ssadagame.infrastructure.config.properties")
class AppConfig
