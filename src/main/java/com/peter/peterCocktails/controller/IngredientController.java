package com.peter.peterCocktails.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.peter.peterCocktails.models.CocktailMatchModel;
import com.peter.peterCocktails.models.IngredientsFormModel;
import com.peter.peterCocktails.models.TCocktail;
import com.peter.peterCocktails.models.TCocktailXIngredient;
import com.peter.peterCocktails.repositories.ICocktailRepository;
import com.peter.peterCocktails.repositories.ICocktailXIngredientRepository;
import com.peter.peterCocktails.repositories.IIngredientRepository;
import com.peter.peterCocktails.services.ICocktailXIngredientService;

@Controller
public class IngredientController {
	
	@Autowired
	private IIngredientRepository ingredientRepository;
	
	@Autowired
	private ICocktailXIngredientService cocktailXIngredientService;
	
	@Autowired
	private ICocktailXIngredientRepository cocktailXIngredientRepository;
	
	//rozhranie, aby sme mohli selectovat z tabulky cocktailov
	@Autowired
	private ICocktailRepository cocktailRepository;
	
	private static final double MIN_PERCENTAGE_MATCH = 0.5;
	
	@GetMapping("/ingredients") //local url: http://localhost:8080/CocktailsAppCourse/ingredients
	public String getIngredients(Model model) {
		
		//do modelu do atributu s nazvom ingredients vlozime vsetky ingrediencie ktore ziskame volanim repository (findAll). "ingredients" mame aj v hrml subore.
		// tu ho naplnime a v html ho budeme citat. v return vratime "ingredients" cim sa odkazama na nazov html suboru
		model.addAttribute("ingredients",ingredientRepository.findAll());
		model.addAttribute("ingredientsFormModel",new IngredientsFormModel());
		
		return "ingredients";
	}
	
	//po odoslani formulara do pola ingredientIds maju prist vsetky zakliknute ingrediencie	
	@PostMapping("/result")
	public String result(@ModelAttribute IngredientsFormModel formModel, Model model) {
		Long[] ingredientIds = formModel.getIngredientIds();
		
		// cocktail id, percentage match
		Map<Long, Integer> percentageMatch = new HashMap<>();
		
		List<TCocktailXIngredient> cocktailIngredientList = 
				cocktailXIngredientService.findAllCocktailsByIngredientsIds(new ArrayList<>(Arrays.asList(formModel.getIngredientIds())));
		
		for (Iterator<TCocktailXIngredient> iterator = cocktailIngredientList.iterator(); iterator.hasNext();) {
			TCocktailXIngredient cocktailXIngredient = iterator.next();
			
			List<TCocktailXIngredient> cocktailIngredientList2 = 
					cocktailXIngredientRepository.findIngredientsByCocktailId(cocktailXIngredient.getCocktail().getCocktailId());
			
			//v premennej je pocet zaznamov, ktore select vratil z databazy
			int listSize = cocktailIngredientList2.size();
			
			//reprezentuje pocet zhodnych ingrediencii v recepte na cocktail
			int matchCount = 0;
			
			//prejdenie zaznamov z databazy
			for(Iterator<TCocktailXIngredient> iterator2 = cocktailIngredientList2.iterator(); iterator2.hasNext();) {
				TCocktailXIngredient cocktailXIngredient2 = (TCocktailXIngredient) iterator2.next();
				
				//navysenie matchCountu ak sa zadana ingrediencia nachadza medzi ingredienciami cocktailu
				for (int i = 0; i<ingredientIds.length; i++) {
					if(ingredientIds[i].equals(cocktailXIngredient2.getIngredient().getIngredientId())) {
						matchCount++;
					}
				}
			}
			
			//ak je % zhoda vacsia ako 50 percent tak pridame id cocktailu s percentualnou zhodou do hashmapy
			if((((double) matchCount) / ((double) listSize)) > MIN_PERCENTAGE_MATCH) {
				percentageMatch.put(cocktailXIngredient.getCocktail().getCocktailId(),
						Integer.valueOf((int) ((((double ) matchCount) / ((double) listSize)) * 100)));
				
			}
		}
			
			//Z HashMapy spravime pole objektov a zavolame metodu sort, ktora zoradi cocktaily podla poctu percent
			Object [] percentageMatchArr = percentageMatch.entrySet().toArray();
			
			Arrays.sort(percentageMatchArr, (Object o1, Object o2) -> ((Map.Entry<String, Integer>) o2).getValue()
					.compareTo(((Map.Entry<String, Integer>) o1).getValue()));
			
			List<CocktailMatchModel> matches = new LinkedList<>();
			
			//naplnanie zoznamu 
			for (int i = 0; i < percentageMatchArr.length; i++) {

				// entry key = id, entry value = percentage match
				Map.Entry<Long, Integer> entry = (Map.Entry<Long, Integer>) percentageMatchArr[i];
				
				//spravime select jedneho zaznamu z tejto tabulky
				TCocktail cocktail = this.cocktailRepository.getOne(entry.getKey());
				
				//v Java entity ziskame udaje - nazov cocktailu a jeho adresu na wiki a k tymto udajom si do noveho objektu priradime % zhodu
				matches.add(new CocktailMatchModel(cocktail.getName(), (entry.getValue() + " %"), cocktail.getUrl()));
				
			}
			
			//do modelu dat pre stranku pridame atribut - zoznam vsetkych objektov cocktail match model a vratime result.
			model.addAttribute("matches", matches);
			
			return "result"; // return result.html from /templates
			
		}
		


		
		
	}
	
	
	
	
	

