package com.StudyHub.StudyHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "StudyHub API", version = "v1", description = "API for StudyHub platform"))
public class OpenAPIConfig {

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("StudyHub API")
                        .version("v1")
                        .description("API documentation for StudyHub platform"));
    }
}
