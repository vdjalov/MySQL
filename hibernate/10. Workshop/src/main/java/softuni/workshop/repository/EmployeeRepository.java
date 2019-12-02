package softuni.workshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import softuni.workshop.domain.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	
	@Query(value = "select concat_ws(\" \", e.first_name, e.last_name) as full_name, e.age, p.name \n" +
				   "from employees as e \n" +
				   "join projects as p \n" +
				   "on e.project_id = p.id \n" +
				   "where e.age > 25 \n" +
				   "order by e.age asc;", nativeQuery = true)
	List<Object[]> employeesOverAgeOfTwentyFive(); 
	
}
