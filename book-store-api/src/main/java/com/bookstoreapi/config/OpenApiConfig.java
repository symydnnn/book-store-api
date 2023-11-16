package com.bookstoreapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    public static final String BEARER = "bearer";
    public static final String AUTHORIZATION = "authorization";
    public static final String BEARER_JWT = "bearer-jwt";
    public static final String READ = "read";
    public static final String WRITE = "write";

    @Bean
    public OpenAPI customOpenApi() {
        final SecurityScheme securityScheme = new SecurityScheme()
                .type(Type.HTTP)
                .scheme(BEARER)
                .in(In.HEADER)
                .name(AUTHORIZATION);

        final Components components = new Components()
                .addSecuritySchemes(BEARER_JWT, securityScheme);

        final SecurityRequirement securityItem = new SecurityRequirement()
                .addList(BEARER_JWT, Arrays.asList(READ, WRITE));

        final Server server = new Server();
        server.setUrl("/");

        return new OpenAPI()
                .components(components)
                .addServersItem(server)
                .info(new Info().title("Book Store Restful API"))
                .addSecurityItem(securityItem);
    }


}

