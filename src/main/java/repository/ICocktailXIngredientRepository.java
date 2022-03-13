package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.TCocktailXIngredient;

public interface ICocktailXIngredientRepository extends JpaRepository<TCocktailXIngredient, Long>{

}
