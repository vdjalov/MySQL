package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

	@Column
	private double discount;

	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@ManyToOne(targetEntity = Car.class)
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;
	
	public double isDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
	
	
}
