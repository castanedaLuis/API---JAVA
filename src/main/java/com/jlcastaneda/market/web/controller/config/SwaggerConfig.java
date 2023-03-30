package com.jlcastaneda.market.web.controller.config;

import org.apache.coyote.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jlcastaneda.market.web.controller"))
                .build().apiInfo(apiEnddoPointInfo());
    }

    private ApiInfo apiEnddoPointInfo(){
        return new ApiInfoBuilder().title("API of products")
                .description("API of services the supermarket")
                .license("Apache 2.0")
                .version("1.0.0")
                .licenseUrl("https://www.apache.org/licences/LICENSE-2.0.html")
                .build();
    }
}
