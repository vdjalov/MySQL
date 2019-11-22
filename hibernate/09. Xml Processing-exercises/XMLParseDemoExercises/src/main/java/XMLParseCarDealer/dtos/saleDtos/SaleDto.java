package XMLParseCarDealer.dtos.saleDtos;

import XMLParseCarDealer.entities.Car;
import XMLParseCarDealer.entities.Customer;

public class SaleDto {


	private double discount;
	private Car car;
	private Customer customer;

	
	public SaleDto(){}


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
