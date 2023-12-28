package com.sincheon.ssadagame.infrastructure.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.sincheon.ssadagame"])
@ConfigurationPropertiesScan("com.sincheon.ssadagame.infrastructure.config.properties")
class AppConfig
