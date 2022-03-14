package service;

import java.util.List;

import model.TCocktailXIngredient;

public interface ICocktailXIngredientService {
	
	public List<TCocktailXIngredient> findAllCocktailsByIngredientsIds(List<Long> ingredientIds);
	
	

}
