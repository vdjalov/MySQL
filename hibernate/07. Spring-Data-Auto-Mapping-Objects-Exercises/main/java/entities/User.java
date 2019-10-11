package entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;




@Entity
@Table(name = "users")
public class User extends BasicEntity {
	

	@Column(nullable = false, unique = true)
	@Pattern(regexp = "(^[a-zA-Z0-9|\\._]+)@([a-z]+\\.[a-z]{2,3})", message = "Please enter a valid email!")
	private String email;
	
	@Column(nullable = false )
	@Pattern(regexp = "(^[\\w\\W]{6,}$)", message = "Password must be exactly six symbols and should contain one upper and one lowe case")
	private String password;
	
	@Column(name = "full_name", nullable = false)
	private String fullName;
	
	@Column(name = "admin", nullable = false)
	private boolean isAdmin;
	
	@Column(name = "logged_in", nullable = false)
	private boolean isLogged;
	
	@ManyToMany(targetEntity = Game.class)
	@JoinTable(name = "users_games", 
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
	private Set<Game> games;
	
	@OneToOne(targetEntity = ShoppingCart.class, mappedBy = "cart")
	private ShoppingCart shoppingCart;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Set<Game> getGames() {
		return games;
	}
	public void setGames(Set<Game> games) {
		this.games = games;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public ShoppingCart getShoppingCard() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCard) {
		this.shoppingCart = shoppingCard;
	}
	
	
	
}
