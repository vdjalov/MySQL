package shampooCompany.shampoos;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import shampooCompany.enums.Size;
import shampooCompany.ingredients.BasicIngredient;
import shampooCompany.interfaces.Shampoo;
import shampooCompany.labels.BasicLabel;


@Entity
@Table(name = "shampoos")
public abstract class BasicShampoo implements Shampoo {

	@Id
	@GeneratedValue
	private long id;
	private String brand;
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private Size size;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BasicLabel label;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<BasicIngredient> ingredients;
	
	public BasicShampoo(){}
	
	public BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel classicLabel) {
		
		this.brand = brand;
		this.price = price;
		this.size = size;
		this.label = classicLabel;
		this.setIngredients(new HashSet<BasicIngredient>());
	}
	
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.setBrand(brand);
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public BasicLabel getLabel() {
		return this.label;
	}

	public void setLabel(BasicLabel label) {
		this.label = label;
	}

	public Set<BasicIngredient> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(Set<BasicIngredient> ingredients) {
		this.ingredients = ingredients;
	};
	
}
