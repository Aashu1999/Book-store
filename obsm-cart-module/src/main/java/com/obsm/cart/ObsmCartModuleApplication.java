package com.obsm.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
*	
*	
	@Author: Ashutosh Dhondi
*
*
*/
@SpringBootApplication
public class ObsmCartModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObsmCartModuleApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	
}

}
