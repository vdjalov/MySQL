package dtos;

import java.util.HashSet;
import java.util.Set;


import entities.Car;
import entities.Supplier;

public class SeedPartsDto {

	private String name;
	private double price;
	private long quantity;
	private Set<Car> cars;
	private Supplier supplier;
	
	public SeedPartsDto() {
		this.cars = new HashSet<Car>();
	}
	
	
	public String getPartName() {
		return name;
	}

	public void setPartName(String partName) {
		this.name = partName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
