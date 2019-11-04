package entities;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

	@Column(nullable = true)
	@Pattern(regexp = "[\\w\\W]{3,}", message = "Name should be longer than three(3) digits.")
	private String name;
	
	@Column
	private double price;
	
	@ManyToMany(targetEntity = Category.class, fetch = FetchType.EAGER)
	@JoinTable(name = "products_categories", 
	joinColumns = @JoinColumn(name = "product_id" , referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Set<Category> categories;
	
	@OneToOne(targetEntity = User.class, optional = true)
	@JoinColumn(name = "seller_id", referencedColumnName = "id")
	private User seller;
	
	@OneToOne(targetEntity = User.class, optional = true)
	@JoinColumn(name = "buyer_id", referencedColumnName = "id")
	private User buyer;

	public Product() {
		this.categories = new HashSet<>();
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
