package com.emsi.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce API")
                        .description(
                                "API REST pour la gestion d'une application e-commerce avec catégories et produits")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Karkouch Zakaria")
                                .email("zakaria.karkouch@example.com")));
    }
}
