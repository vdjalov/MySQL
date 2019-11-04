package dtos;

import java.util.ArrayList;
import java.util.List;

public class SuccessfullySoldProductsDto {

	private String firstName;
	private String lastName;
	private List<SuccsessfullySoldProductsBuyerDto> soldProducts;
	
	public SuccessfullySoldProductsDto() {
		this.soldProducts = new ArrayList<>();
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<SuccsessfullySoldProductsBuyerDto> getBuyers() {
		return soldProducts;
	}
	public void setBuyers(List<SuccsessfullySoldProductsBuyerDto> buyers) {
		this.soldProducts = buyers;
	}
}
