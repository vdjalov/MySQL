package alararestaurant.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

	@NotNull
	private String customer;
	
	@Column(name = "date_time", nullable = false)
	private String dateTime;
	
	@Column(nullable = false, columnDefinition = "varchar(32) default 'ForHere'")
	@Enumerated(value = EnumType.STRING)
	private OrderType orderType;
	
	@ManyToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;

	@OneToMany(targetEntity = OrderItem.class, mappedBy = "order")
	private List<OrderItem> orderItems;
	
	public Order(){
		this.orderItems = new ArrayList<>();
	};
	
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
	
}
