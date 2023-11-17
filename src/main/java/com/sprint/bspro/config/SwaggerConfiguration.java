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

	/** *@apiInfo method returns an instance of ApiInfo,which contains information about the Book Store App's REST APIs. * *
	   **@return an instance of ApiInfo.
	*/
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

		/** @api method returns an instance of Docket, which is used for configuring Swagger documentation for the Book Store App's API. 
		 * @return is an instance of Docket configured.
		*/
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
