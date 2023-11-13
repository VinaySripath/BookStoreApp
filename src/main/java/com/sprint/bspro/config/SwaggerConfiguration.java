package com.sprint.bspro.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

		private ApiInfo apiInfo() {
	        return new ApiInfo("Book Store App",
	                "REST APIs for BookStoreApp-Pro",
	                "1.0",
	                "Terms of service",
	                new Contact("@vinay","", "vinaysripath@gmail.com"),
	                "License of API",
	                "API license URL",
	                Collections.emptyList());
	    }

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.sprint.bspro.controller"))
	                .paths(PathSelectors.any())
	                .build();
	    }
}
