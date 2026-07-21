package com.elearning.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI elearningOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("E-Learning Platform API")
                        .description("Enterprise Learning Management System REST APIs")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Garv Gupta")
                                .email("garv@example.com"))
                        .license(new License()
                                .name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }
}