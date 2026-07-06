package com.teamflow.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "TeamFlow API",
                version = "1.0",
                description = "Project Management System Backend built with Spring Boot",
                contact = @Contact(
                        name = "Raghunath Toparam",
                        email = "raghunath@gmail.com"
                ),
                license = @License(
                        name = "MIT License"
                )
        )
)
public class SwaggerConfig {

}