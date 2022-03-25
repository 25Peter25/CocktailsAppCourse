package com.peter.peterCocktails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peter.peterCocktails.models.TIngredient;

public interface IIngredientRepository extends JpaRepository<TIngredient, Long>{
	
	

}
