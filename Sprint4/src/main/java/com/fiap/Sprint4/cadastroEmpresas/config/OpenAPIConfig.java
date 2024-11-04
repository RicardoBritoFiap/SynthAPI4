package com.fiap.Sprint4.cadastroEmpresas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Cadastro de Empresas")
                        .description("API para cadastro e gerenciamento de empresas")
                        .version("1.0.0"));
    }
}