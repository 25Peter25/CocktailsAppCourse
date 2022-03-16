package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.TIngredient;

public interface IIngredientRepository extends JpaRepository<TIngredient, Long>{
	
	

}
