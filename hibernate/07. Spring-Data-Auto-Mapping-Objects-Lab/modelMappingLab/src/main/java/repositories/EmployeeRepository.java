package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query(value = "select * " +  
			       "from employees as e " +
				   "where year(e.birth_date) < '1990' " +
				   "order by e.salary desc;", nativeQuery = true)
	List<Employee> findAllEmployeesBornBefore1990();
	
	
	
}
