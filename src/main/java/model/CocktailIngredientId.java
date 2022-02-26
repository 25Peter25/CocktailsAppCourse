package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //anotacia ktora nevytvara Entitu, ale deklaruje ze do nej budu vlozene-embedded ine entity
public class CocktailIngredientId implements Serializable{

	@Column(name = "cocktail_id")
	private Long cocktailId;
	
	@Column(name = "ingredient_id")
	private Long ingredientId;


	public CocktailIngredientId(Long cocktailId, Long ingredientId) {
		super();
		this.cocktailId = cocktailId;
		this.ingredientId = ingredientId;
	}

	public CocktailIngredientId() {
		super();
	}


	
	
	
	
	
	
}
