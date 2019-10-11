package DTO;

import java.util.Date;

public class GameDetailsDto {

	private String title;
	private double price;
	private String description;
	private Date releaseDate;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	public String toString() {
		
		return String.format("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s",
				this.getTitle(), this.getPrice(), this.getDescription(), this.getReleaseDate());
	}
	
	
}
