package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
	@Query(value = "select c.name, count(c.name) as product_count, avg(p.price) as average_price, sum(p.price) as price \n" + 
			       "from categories as c \n" +
				   "join products_categories as pc \n" +
				   "on pc.category_id = c.id \n" +
				   "join products as p \n" +
				   "on p.id = pc.product_id \n" +
				   "group by c.name \n" +
				   "order by product_count; ", nativeQuery = true)		
	List<Object[]> categoriesByProductsCount();
}
