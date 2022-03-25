package com.peter.peterCocktails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peter.peterCocktails.models.TCocktail;

//TCocktail - objekt tabulky, long - datovy tip IDcka
public interface ICocktailRepository extends JpaRepository<TCocktail, Long>{

}
