package com.example.cineket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Cineket")
                        .version("1.0.0")
                        .description("API REST para la gestión de reservas de entradas de cine. " +
                                "Permite gestionar películas, funciones, salas, asientos y reservas de forma completa.")
                        .contact(new Contact()
                                .name("Cineket")
                                .url("https://github.com/agusaguerom/Cineket")));
    }
}

