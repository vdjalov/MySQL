package XMLParseCarDealer.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

	@Column
	private double discount;
	
	@OneToOne(targetEntity = Car.class)
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;
	
	@ManyToOne()
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	public Sale(){}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	};
	
	
}
