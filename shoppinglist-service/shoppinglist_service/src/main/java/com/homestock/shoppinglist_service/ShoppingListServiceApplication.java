package com.homestock.shoppinglist_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingListServiceApplication.class, args);
	}

}
