package shampooCompany.ingredients;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import shampooCompany.interfaces.Ingredient;
import shampooCompany.shampoos.BasicShampoo;

@Entity
@Table(name = "ingredients")
public abstract class BasicIngredient implements Ingredient {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private BigDecimal price;
	
	@ManyToMany(targetEntity = BasicShampoo.class, mappedBy = "ingredients")
	private List<BasicShampoo> shampoos;
	
	public BasicIngredient() {}
	
	public BasicIngredient(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
	this.name = name;
		
	}
	
	
	public int getId() {
		return this.id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public BigDecimal getPrice() {
		return this.price;
	}
	
	
	public void setPrice(BigDecimal price) {
		this.price = price;
		
	}

	public List<BasicShampoo> getShampoos() {
		return Collections.unmodifiableList(this.shampoos);
	}

	public void setShampoos(List<BasicShampoo> shampoos) {
		
	}

	
}
