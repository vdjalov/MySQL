package alararestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import alararestaurant.domain.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Employee findByName(String name);
}
