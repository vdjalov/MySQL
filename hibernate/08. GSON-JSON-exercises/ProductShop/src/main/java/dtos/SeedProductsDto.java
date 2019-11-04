package dtos;


import java.util.HashSet;
import java.util.Set;

import entities.Category;
import entities.User;

public class SeedProductsDto  {
	
	
	private String name;
	private double price;
	private Set<Category> categories;
	private User seller;
	private User buyer;

	public SeedProductsDto() {
		this.categories = new HashSet<Category>();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

}
