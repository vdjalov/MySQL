package alararestaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import alararestaurant.domain.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	
	@Query(value = "select e.name, o.customer, i.name as item_name, i.price, oi.quantity \n" + 
			"from employees as e \n" +
			"join orders as o on \n" +
			"o.employee_id = e.id \n" +
			"join order_items as oi \n" +
			"on o.id = oi.order_id \n" +
			"join items as i \n" +
			"on oi.item_id = i.id \n" +
			"join positions as p \n" +
			"on e.posititon_id = p.id \n" +
			"where p.name = 'Burger Flipper' \n" +
			"order by e.name, o.id;", nativeQuery = true)
	List<Object[]> getOrdersFinishedByTheBurgerFlippers();
}
