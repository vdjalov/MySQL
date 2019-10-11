package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart extends BasicEntity {
	
	@OneToOne(targetEntity = User.class)
	private Set<Game> cart;
	
		public ShoppingCart() {
			this.cart = new HashSet<>();
		}
		
		
		public void addItem(Game game) {
			this.cart.add(game);
		}
		
		public void removeItem(Game game) {
			this.cart.remove(game);
		}
		
}
