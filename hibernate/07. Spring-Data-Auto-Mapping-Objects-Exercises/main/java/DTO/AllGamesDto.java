package DTO;

public class AllGamesDto {

	private String title;
	private double price;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String toString() {
		return String.format("%s %.2f", this.title, this.price);
		
	}
}
