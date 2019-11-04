package dtos;

import java.util.HashSet;
import java.util.Set;
import entities.Product;

public class SeedCategoriesDto {


	
	private String name;
	private Set<Product> products;
	
	public SeedCategoriesDto() {
		this.products = new HashSet<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
