package dtos;



import entities.Car;
import entities.Customer;

public class SeedSaleDto {
	
	private double discount;
	private Customer customer;
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
