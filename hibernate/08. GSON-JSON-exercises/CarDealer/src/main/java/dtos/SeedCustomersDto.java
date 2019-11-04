package dtos;

import java.util.HashSet;
import java.util.Set;


import entities.Sale;

public class SeedCustomersDto {


	private String name;
	private String birthDate;
	private boolean isYoungDriver;
	private Set<Sale> sales;
	
	public SeedCustomersDto() {
		this.setSales(new HashSet<Sale>());
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isYoungDriver() {
		return isYoungDriver;
	}
	public void setYoungDriver(boolean isYoungDriver) {
		this.isYoungDriver = isYoungDriver;
	}


	public Set<Sale> getSales() {
		return sales;
	}


	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}
}
