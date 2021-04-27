package com.cts.salesprocessing;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableFeignClients("com.cts.salesprocessing.feign")
@EnableCircuitBreaker
@EnableCaching
@EnableSwagger2
@EnableEurekaClient
public class SalesProcessingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesProcessingServiceApplication.class, args);
	}
	
	/**
	 * Bean for Swagger Configuration
	 * 
	 * @return - Swagger Docket
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cts.salesprocessing")).build().apiInfo(apiDetails());

	}

	/**
	 * 
	 * @return Api information
	 */
	private ApiInfo apiDetails() {

		return new ApiInfo("Sales Processing Service", "Microservice Form Use Case", "1.0",
				"Free To Use", new springfox.documentation.service.Contact("Admin", "", "admin@cognizant.com"),
				"API Licesence", "....", Collections.emptyList());
	}

}