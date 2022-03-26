UPDATE co.ingredients i SET name='raspberry liquere' WHERE ingredient_id = 100;
UPDATE co.ingredients i SET name='orange liqueur' WHERE ingredient_id = 68;
UPDATE co.ingredients i SET name='martinique rum' WHERE ingredient_id = 81;
UPDATE co.ingredients i SET name='scotch whisky' WHERE ingredient_id = 107;
UPDATE co.ingredients i SET name='worcester sauce' WHERE ingredient_id = 56;
UPDATE co.ingredients i SET name='lagav. 16y whisky' WHERE ingredient_id = 108;

--Kedze mazem z tabulky, kde je primarny kluc dvojica, musim naraz delete oba udaje - vymazanie duplicitnej ingrediencie
DELETE FROM co.t_cocktails_x_ingredients WHERE ingredient_id=55 AND cocktail_id=36;
DELETE FROM co.ingredients WHERE ingredient_id=55;

--VYHLADANIE DUPLICITNYCH POLOZIEK!!!:
--SELECT cocktail_id,name FROM co.t_cocktails WHERE cocktail_id NOT IN (SELECT DISTINCT ON (name) cocktail_id FROM co.t_cocktails);
--Vymazanie duplicitneho cocktailu
DELETE FROM co.t_cocktails_x_ingredients 
WHERE cocktail_id = (SELECT cocktail_id FROM co.t_cocktails WHERE cocktail_id NOT IN (SELECT DISTINCT ON (name) cocktail_id FROM co.t_cocktails));

DELETE FROM co.t_cocktails WHERE cocktail_id = (SELECT cocktail_id FROM co.t_cocktails WHERE cocktail_id NOT IN (SELECT DISTINCT ON (name) cocktail_id FROM co.t_cocktails));