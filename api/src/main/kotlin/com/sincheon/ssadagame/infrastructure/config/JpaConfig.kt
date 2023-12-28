package com.sincheon.ssadagame.infrastructure.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.sincheon.ssadagame.domain"])
@EntityScan(basePackages = ["com.sincheon.ssadagame.domain"])
class JpaConfig
