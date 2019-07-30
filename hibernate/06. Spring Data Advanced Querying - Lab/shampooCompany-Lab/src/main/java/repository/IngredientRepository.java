package repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entities.Ingredient;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
	
	@Query( value = "select sum(i.price) as Output\n" +
			"from ingredients as i\n" +
			"join shampoos_ingredients as si\n" +
			"on i.id = si.ingredient_id\n" +
			"join shampoos as s\n" + 
			"on si.shampoo_id = s.id\n" +
			"where s.brand = :shampooBrand", nativeQuery = true)
	String findIngredientsSum(@Param(value = "shampooBrand") String shampooBrand);
		
	@Modifying
	@Transactional
	@Query(value = "delete from ingredients where name = :ingredientName", 
			nativeQuery = true)
	void deleteIngredientByName(@Param(value = "ingredientName") String ingredientName);
	
	@Modifying
	@Transactional
	@Query(value = "update ingredients set price = price * 1.1", nativeQuery = true)
	void increaseCostsBy10PercentOnEveryIngredient();
	
	@Modifying
	@Transactional
	@Query(value = "update ingredients set price = price * :percentage where name in (:ingredientsNames)", nativeQuery = true)
	void updateIngredientPriceByGivenNameAndPercentageIncrease(
		@Param(value = "percentage") double percentage, @Param(value = "ingredientsNames") List<String> ingredientsNames);
}















