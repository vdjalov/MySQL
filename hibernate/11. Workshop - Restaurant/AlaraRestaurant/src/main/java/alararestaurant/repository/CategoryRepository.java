package alararestaurant.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import alararestaurant.domain.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByName(String name);
	
	
	@Query(value = "select i.category_id, i.name, i.price, \n " +
			   "(select count(ii.name) from  \n " +
			   "items as ii  \n " +
			   "where ii.category_id = i.category_id) as items_count,  \n " +
			   "(select sum(iii.price) \n " +
			   "from items as iii \n " +
			   "where iii.category_id = i.category_id) as by_price \n " +
			   "from items as i  \n " +
			   "order by items_count desc, by_price desc;" , nativeQuery = true)
	List<Object[]> categriesByCountOfItems();
	
}
