package com.zappts.tarefas.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Tarefas")
                        .description("API para gerenciamento de tarefas.")
                        .version("1.0.0"));
    }
}