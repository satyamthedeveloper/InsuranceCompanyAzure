package com.cts.customerregistration;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class CustomerRegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRegistrationServiceApplication.class, args);
	}

	/**
	 * Bean for Swagger Configuration
	 * 
	 * @return - Swagger Docket
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cts.customerregistration")).build().apiInfo(apiDetails());

	}

	/**
	 * 
	 * @return Api information
	 */
	private ApiInfo apiDetails() {

		return new ApiInfo("Customer Registration Service", "Microservice Form Use Case", "1.0",
				"Free To Use", new springfox.documentation.service.Contact("Admin", "", "admin@cognizant.com"),
				"API Licesence", "....", Collections.emptyList());
	}

}
