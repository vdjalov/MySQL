package XMLParseCarDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import XMLParseCarDealer.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

	
	@Query(value = "select * \n" +
				   "from cars as c \n" +
				   "where c.make = 'Toyota' \n" +
				   "order by c.model asc, c.travelled_distance desc;", nativeQuery = true)
	public List<Car> getAllCarsMakeToyota();
	
	
	@Query(value = "select c.id, c.make, c.model, c.travelled_distance, p.name, p.price \n" +
				   "from parts_cars as pc \n" +
				   "join parts as p \n" +
				   "on pc.part_id = p.id \n" +
				   "join cars as c \n" +
				   "on c.id = pc.car_id \n" +
				   "order by c.id;", nativeQuery = true)
	public List<Object[]> getAllCarsWithTheirParts();
	
	
	@Query(value = "select c.make, c.model, c.travelled_distance, \n" +  
				   "(select cm.name \n" +
				   "from customers as cm \n" + 
				   "where cm.id = s.customer_id) as customer_name, \n" +
				   "(select s.discount \n" +
				   "from customers as cm \n" +
				   "where cm.id = s.customer_id) as discount, \n" +
				   "(select sum(p.price) \n" +
				   "from parts as p \n" +
				   "join parts_cars as pc \n" +
				   "on p.id = pc.part_id \n" +
				   "where pc.car_id = s.car_id) as  price \n" +
				   "from cars as c \n" +
				   "join sales as s \n" +
				   "on s.car_id = c.id;", nativeQuery = true)				
	public List<Object[]> getAllSalesInfo();
}
