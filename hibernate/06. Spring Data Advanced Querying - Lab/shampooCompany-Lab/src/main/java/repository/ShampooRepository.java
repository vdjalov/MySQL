package repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entities.Shampoo;
import entities.Size;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long>{
	List<Shampoo> findByBrand(String brand);
	List<Shampoo> findBySizeOrderById(Size size);
	List<Shampoo> findByLabelIdOrSizeOrderByPriceAsc(long labelId, Size size);
	List<Shampoo> findByPriceLessThan(BigDecimal price);
	
	@Query(value = "select s.brand\n" +
				   "from shampoos as s\n" + 
				   "join shampoos_ingredients as si\n" +
				   "on s.id = si.shampoo_id\n" +
				   "join ingredients as i\n" + 
				   "on i.id = si.ingredient_id\n" +
				   "where i.name in (:ingredients);",
				   				nativeQuery = true)
	List<String> searchByIgredients(@Param(value = "ingredients") List<String> ingredients);
	
	
	@Query(value =  "select distinct s.brand\n" +
					"from shampoos as s\n" + 
					"join shampoos_ingredients as si\n" +
					"on s.id = si.shampoo_id\n" +
					"join ingredients as i\n" + 
					"on i.id = si.ingredient_id\n" +
					"where (select count(sic.shampoo_id)\n" +
					"from shampoos_ingredients as sic\n" +
					"where sic.shampoo_id = si.shampoo_id\n" +
					") < :count\n", nativeQuery = true)
	List<String> searchByShampooCountLessThan(@Param("count") int count);
}
