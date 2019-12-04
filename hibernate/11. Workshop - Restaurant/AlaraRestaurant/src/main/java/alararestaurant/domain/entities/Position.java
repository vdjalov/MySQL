package alararestaurant.domain.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "positions")
public class Position extends BaseEntity{
	
	@Column(nullable = false, unique = true)
	@Size(min = 3, max = 30)
	private String name;

	@OneToMany(targetEntity = Employee.class, mappedBy = "position")
	private List<Employee> employees;
	
	public Position(){
		this.employees = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
	
}
