package com.example.socialnetwork.config;

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
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Social Network Backend API")
                        .description("This is social network application project api and developed by hieudinh and lannguyen")
                        .version("1.0")
                        .contact(new Contact().name("Mr.Hieu").email("dinhvanhieu2002@gmail.com").url("https://dinhvanhieu2002.github.io"))
                        .license(new License().name("Licence")))
                .externalDocs(new ExternalDocumentation().url("https://dinhvanhieu2002.github.io").description("This is external url"));
    }
}
