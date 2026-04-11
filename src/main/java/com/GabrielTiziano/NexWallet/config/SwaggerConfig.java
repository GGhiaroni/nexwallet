package com.GabrielTiziano.NexWallet.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi(){

        Contact contact = new Contact();
        contact.setUrl("https://github.com/GGhiaroni");
        contact.name("Gabriel Ghiaroni Tiziano");
        contact.setEmail("gghiaronitiziano@gmail.com");

        Info info = new Info();
        info.title("NexWallet API");
        info.version("v1.0");
        info.description("API de gerenciamento de portfólio de criptomoedas com cotações em tempo real.");
        info.contact(contact);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme));
    }
}