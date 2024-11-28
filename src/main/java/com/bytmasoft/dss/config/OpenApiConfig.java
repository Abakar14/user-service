package com.bytmasoft.dss.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mahamat Abakar",
                        email = "abakar61@web.de",
                        url = ""
                ),
                description = "OpenApi for User Service",
                title = "User Service",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "http://localhost"
                ),
                termsOfService = "Term of Service"

        ),
          security = {
                @SecurityRequirement(
                        name = "BearerAuth"
                )
        }
)
@SecuritySchemes(
        value = {
                @SecurityScheme(
                        name = "BearerAuth",
                        description = "Jwt authentication",
                        scheme = "Bearer",
                        type = SecuritySchemeType.HTTP,
                        bearerFormat = "JWT",
                        in = SecuritySchemeIn.HEADER
                )
        }
)
@Configuration
public class OpenApiConfig {
}
