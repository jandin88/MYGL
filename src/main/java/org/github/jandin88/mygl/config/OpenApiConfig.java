package org.github.jandin88.mygl.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
              .info(new Info()
                    .title("MYGL API")
                    .version("1.0")
                    .description("Documentação da API com suporte a JWT"))
              .components(new Components()
                    .addSecuritySchemes("bearerAuth", new SecurityScheme()
                          .type(SecurityScheme.Type.HTTP)
                          .scheme("bearer")
                          .bearerFormat("JWT")
                          .in(SecurityScheme.In.HEADER)
                          .name("Authorization")
                    ))
              .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
