package com.teammacc.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.teammacc.stock.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addResourceHandlers( ResourceHandlerRegistry registry )
            {
                registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
                registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Stock API",
                "Information related to api exposed by teammacc system.",
                "1.0",
                "https://www.teammacc.com/termsandconditions",
                new springfox.documentation.service.Contact("Team MACC", "http://www.teammacc.com", "support@teammacc.com"),
                "License of API",
                "https://www.teammacc.com/privacypolicy",
                Collections.emptyList());
    }
}