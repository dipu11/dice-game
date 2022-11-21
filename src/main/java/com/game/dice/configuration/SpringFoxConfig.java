package com.game.dice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;


/**
 * Created by Dipu on 11/21/22.
 */

@EnableSwagger2WebMvc
@Configuration
public class SpringFoxConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo("MyApp Rest APIs",
                "APIs for MyApp.",
                "1.0",
                "Terms of service",
                new Contact("test", "www.org.com", "test@emaildomain.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }



   /* *//*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
*//*
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

   *//* private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Dice Game API")
                .description("Multiplayer Dice game")
                .termsOfServiceUrl("test.dicegame.com")
                .contact(new Contact("Public project", "http://www.mysite.com", "contact@dicegame.com"))
                .licenseUrl("acdsfdsf").version("1.0").build();
    }*//*

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("TigerIT HRM").description("")
                .termsOfServiceUrl("https://www.tigerhrm.tigerit.com/api")
                .contact(new Contact("TigerIT BD Ltd", "http://www.tigerit.com", "contact@tigerit.com"))
                .license("Open Source").licenseUrl("https://www.tigerit.com").version("1.1.0").build();
    }*/


}
