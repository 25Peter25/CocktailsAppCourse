package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import repository.IIngredientRepository;

@Controller
public class IngredientController {
	
	
	
	@Autowired
	private IIngredientRepository ingredientRepository;
	
	@GetMapping("/ingredients") //local url: http://localhost:8080/CocktailsAppCourse/ingredients
	// tu naozaj... file:///C:/Users/Peter/git/CocktailsAppCourse/src/main/resources/templates/ingredients.html
	public String getIngredients(Model model) {
		
		//do modelu do atributu s nazvom ingredients vlozime vsetky ingrediencie ktore ziskame volanim repository (findAll). "ingredients" mame aj v hrml subore.
		// tu ho naplnime a v html ho budeme citat. v return vratime "ingredients" cim sa odkazama na nazov html suboru
		model.addAttribute("ingredients",ingredientRepository.findAll());
		
		return "ingredients";
	}

}
