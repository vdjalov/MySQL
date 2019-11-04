package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;



@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

	@Column
	@Pattern(regexp = "[\\w\\W]{3,15}", message = "The name should be between and 15 letters.")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products;
	
	
	public Category() {
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
