package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	@Query(value = "select p.name, p.price, u.first_name, u.last_name\n" +
				   "from products as p\n" +
			       "join users as u\n" + 
				   "on u.id = p.seller_id\n" +
                   "where p.buyer_id is null and p.price > 500 and p.price < 1000;"
				   , nativeQuery = true)
	List<Object[]> findAllProductsWithoutABuyer();
	
	
	@Query(value = "select\n" +
				   "(select uu.last_name\n" +
				   "from users as uu\n" +
				   "where uu.id = p.seller_id) as seller_last_name,\n" +
				   "(select uu.first_name\n" +
				   "from users as uu\n" +
				   "where uu.id = p.seller_id ) as seller_first_name,\n" +
				   "u.first_name as buyer_first_name, u.last_name as buyer_last_name, p.name, p.price\n" +
				   "from users as u\n" +
				   "join products as p\n" +
				   "on u.id = p.buyer_id order by last_name, first_name;", nativeQuery = true)
	List<Object[]> findAllSuccsessFullySoldProducts();
	
}
