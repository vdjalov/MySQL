package alararestaurant.domain.entities;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
@Table(name = "employees")
public class Employee extends BaseEntity { 

	@Column()
	@Size(min = 3, max = 30)
	private String name;
	
	@Column
	@Min(value = 15)
	@Max(value = 80)
	private int age;
	
	@ManyToOne(targetEntity = Position.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "posititon_id", referencedColumnName = "id")
	private Position position;

	@OneToMany(targetEntity = Order.class, mappedBy = "employee")
	private List<Order> orders;
	
	public Employee(){
		this.setOrders(new ArrayList<>());
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
