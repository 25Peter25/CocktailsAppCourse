package com.peter.peterCocktails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peter.peterCocktails.models.TCocktailXIngredient;

public interface ICocktailXIngredientRepository extends JpaRepository<TCocktailXIngredient, Long>{
	
	//Vracanie vsetkych zaznamov v ktorych sa nachadza ID pozadovaneho cocktailu 
	@Query("SELECT ci FROM TCocktailXIngredient ci WHERE ci.id.cocktailId = :cocktailId")
	public List<TCocktailXIngredient> findIngredientsByCocktailId(@Param("cocktailId") Long cocktailId);

}
