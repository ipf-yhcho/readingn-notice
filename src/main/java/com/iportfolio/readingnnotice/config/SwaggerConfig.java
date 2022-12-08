package com.iportfolio.readingnnotice.config;

import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String PROJECT_NAME = "readingn-notice";

    public static final String BASE_PACKAGE = "com.iportfolio.readingnnotice";

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .consumes(Set.of(APPLICATION_JSON_CHARSET_UTF_8, APPLICATION_X_WWW_FORM_URLENCODED))
            .produces(Set.of(APPLICATION_JSON_CHARSET_UTF_8))
            .apiInfo(
                new ApiInfoBuilder()
                    .title(PROJECT_NAME + " API")
                    .description(PROJECT_NAME + " API Docs")
                    .build()
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
            .build()
            .useDefaultResponseMessages(false);
    }
}
