package com.example.cfg;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

@Configuration
public class SwaggerCfg {

    @Autowired private SwaggerProps swaggerProps;

    @Value("${springdoc.authorization-url}")
    private String authorizationUrl;

    @Value("${springdoc.token-url}")
    private String tokenUrl;

    @Bean
    public OpenAPI springOpenApi() {

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("OAuth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(authorizationUrl)
                                                .tokenUrl(tokenUrl)))))
                    .addSecurityItem(new SecurityRequirement()
                            .addList("OAuth2", Arrays.asList("read", "write")))
                .info(new Info().title(swaggerProps.title)
                .description(swaggerProps.description)
                .version(swaggerProps.version)
                .contact(new Contact()
                        .name(swaggerProps.contactName)
                        .url(swaggerProps.contactUrl)
                        .email(swaggerProps.contactEmail)));
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "springdoc")
    public static class SwaggerProps {

        private String contactName;
        private String contactUrl;
        private String contactEmail;

        private String title;
        private String description;
        private String version;

    }
}
