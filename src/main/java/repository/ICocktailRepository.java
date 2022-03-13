package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.TCocktail;

//TCocktail - objekt tabulky, long - datovy tip IDcka
public interface ICocktailRepository extends JpaRepository<TCocktail, Long>{

}
