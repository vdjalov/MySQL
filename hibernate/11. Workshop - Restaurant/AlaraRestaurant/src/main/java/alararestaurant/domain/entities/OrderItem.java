package alararestaurant.domain.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

	@NotNull
	@ManyToOne(targetEntity = Order.class)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;
	
	
	@NotNull
	@ManyToOne(targetEntity = Item.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	
	@Column
	@Min(value = 1)
	private int quantity;


	
	public OrderItem() {}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
