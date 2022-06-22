package com.practice.shoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ShoppingmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingmallApplication.class, args);
	}

}