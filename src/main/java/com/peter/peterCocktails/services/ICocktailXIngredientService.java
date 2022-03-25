package com.peter.peterCocktails.services;

import java.util.List;

import com.peter.peterCocktails.models.TCocktailXIngredient;

public interface ICocktailXIngredientService {
	
	public List<TCocktailXIngredient> findAllCocktailsByIngredientsIds(List<Long> ingredientIds);
	
	

}
