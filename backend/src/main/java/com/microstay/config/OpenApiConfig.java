package com.microstay.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI microStayOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("MicroStay API").version("v1").description("MVP API for MicroStay"))
            .externalDocs(new ExternalDocumentation().description("MicroStay").url("https://microstay.com"));
    }
}



