package entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "games")
public class Game extends BasicEntity {

	@Column(unique = true)
	@Pattern(regexp = "(^[A-Z]+[\\w\\W]+)", 
		message = "Title must start with an uppercase letter and has more than 3 symbols length.")
	private String title;
	
	@Column
	@Pattern(regexp = "([\\w\\W]{11})", message = "Trailer ID must be exactly 11 characters.")
	private String trailer;
	
	@Column(name = "image_url")
	@Pattern(regexp = "(^https+|^http+):\\/\\/([\\w\\W]+)", message = "URL incorrect.")
	private String imageURL;
	
	@Column(name = "game_size")
	@DecimalMin(value = "0.0", message = "Size must be a positive number")
	@Digits(integer = 12, fraction = 1)
	private double size;
	
	@Column
	@DecimalMin(value = "0.0" ,message = "Price must be a positive number.")
	@Digits(fraction = 2, integer = 12)
	private double price;
	
	@Column
	@Pattern(regexp = "([\\w\\W]{20,})", message = "Desctiption should be at least 20 symbols.")
	private String description;
	
	@Column(name = "release_date")
	private Date releaseDate;
	
	@ManyToMany(targetEntity = User.class, mappedBy = "games")
	private Set<User> users;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getImage() {
		return imageURL;
	}
	public void setImage(String imageURL) {
		this.imageURL = imageURL;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
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


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
	
	
}
