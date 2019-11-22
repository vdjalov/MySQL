package XMLParseCarDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import XMLParseCarDealer.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	
	@Query(value = "select * \n" + 
                   "from customers as c \n" +
                   "order by c.birth_date, c.is_young_driver;",	nativeQuery = true)
	List<Customer> getAllCustomersOrderedByBirthDate();

	
	@Query(value = "select c.name, count(c.name) as boughtCars, \n" + 
				   "(select sum(p.price) \n" + 
				   "from parts as p \n" +
				   "join parts_cars as pc \n" +
				   "on pc.part_id = p.id \n" +
				   "where pc.car_id = s.car_id) as spentMoney \n" +
				   "from sales as s \n" +
				   "join customers as c \n" +
				   "on s.customer_id = c.id \n" +
				   "group by c.name;", nativeQuery = true)
	List<Object[]> getTotalSalesByCustomer();
}
