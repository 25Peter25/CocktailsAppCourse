package com.peter.peterCocktails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.peter.peterCocktails.repositories"})
public class PeterCocktailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeterCocktailsApplication.class, args);
	}

}
