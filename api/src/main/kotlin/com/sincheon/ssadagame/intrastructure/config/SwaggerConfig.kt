package com.sincheon.ssadagame.intrastructure.config

import com.sincheon.ssadagame.intrastructure.config.properties.SwaggerProperties
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(swaggerProperties: SwaggerProperties): OpenAPI {
        return OpenAPI()
            .info(apiInfo(swaggerProperties))
    }

    private fun apiInfo(swaggerProperties: SwaggerProperties) = Info()
        .title(swaggerProperties.title)
        .description(swaggerProperties.description)
        .version(swaggerProperties.version)
}
