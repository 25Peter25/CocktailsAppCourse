package com.peter.peterCocktails.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.peterCocktails.models.TCocktailXIngredient;
import com.peter.peterCocktails.repositories.ICocktailXIngredientRepository;
import com.peter.peterCocktails.services.ICocktailXIngredientService;

@Service
public class CocktailXIngredientServiceImpl implements ICocktailXIngredientService{
	
	@Autowired 
	ICocktailXIngredientRepository cocktailXIngredientRepository;

	@Override
	public List<TCocktailXIngredient> findAllCocktailsByIngredientsIds(List<Long> ingredientIds) {
		
		//findAll vrati zoznam vsetkych zaznamov. Filter - kazdy zaznam ktory prejde filtrom bude obsahovat ingredientId ktory mame v zozname. Distinc-jedinecne zaz.
		return cocktailXIngredientRepository.findAll().stream()
				.filter(record -> ingredientIds.contains(record.getIngredient().getIngredientId())).distinct()
				.collect(Collectors.toList());
		
	}
	
	

}
