package alararestaurant.domain.dtos.JSON;

import org.springframework.stereotype.Service;

import com.google.gson.annotations.Expose;

@Service
public class ItemsAndCategoryImportDto {

	@Expose
	private String name;
	
	@Expose
	private double price;
	
	@Expose
	private String category;
	
	
	public ItemsAndCategoryImportDto() {}


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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
