CREATE DATABASE db_cocktails
    WITH 
    OWNER = postgres
    TEMPLATE = template0
    ENCODING = 'UTF8'
    LC_COLLATE = 'Slovak_Slovakia.1250'
    LC_CTYPE = 'Slovak_Slovakia.1250'
    CONNECTION LIMIT = -1;
    
    CREATE SCHEMA co
    AUTHORIZATION postgres;
    
    
	-- table ingredients
    CREATE TABLE co.ingredients
(
    ingredient_id bigserial NOT NULL,
    name character varying(50) NOT NULL,
    PRIMARY KEY (ingredient_id)
);
ALTER TABLE IF EXISTS co.ingredients
    OWNER to postgres;
    
    
    
    -- table cocktails:
    CREATE TABLE co.t_cocktails
(
    cocktail_id bigserial NOT NULL,
    name character varying(50) NOT NULL,
    url character varying(1000) NOT NULL,
    PRIMARY KEY (cocktail_id)
);
ALTER TABLE IF EXISTS co.t_cocktails
    OWNER to postgres;
    
    
    -- krizova tabulka
    CREATE TABLE co.t_cocktails_x_ingredients
(
    cocktail_id bigserial NOT NULL,
    ingredient_id bigserial NOT NULL,
    FOREIGN KEY (cocktail_id)
        REFERENCES co.t_cocktails (cocktail_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (ingredient_id)
        REFERENCES co.ingredients (ingredient_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
ALTER TABLE IF EXISTS co.t_cocktails_x_ingredients
    OWNER to postgres;