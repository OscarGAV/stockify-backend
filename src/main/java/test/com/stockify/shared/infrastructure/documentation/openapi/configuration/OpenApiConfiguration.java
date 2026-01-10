package test.com.stockify.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI StockifyOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("Stockify API")
                        .description("API documentation for Stockify application with DDD, Hexagonal Architecture, and CQRS pattern")
                        .version("1.0.0")
                        .contact(new Contact())
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")));

        final String securityScheme = "bearerAuth";
        Components components = new Components();
        components.addSecuritySchemes(securityScheme, new SecurityScheme()
                .name(securityScheme)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT"));
        openAPI.components(components);

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityScheme);
        openAPI.addSecurityItem(securityRequirement);

        return openAPI;
    }
}
