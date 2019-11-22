package XMLParseCarDealer.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

	@Column
	private String name;
	
	@Column
	private double price;
	
	@Column
	private String quantity;
	
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private Supplier supplier;
	
	@ManyToMany(targetEntity = Car.class, mappedBy = "parts")
	private Set<Car> cars;
	
	public Part(){
		this.cars = new HashSet<>();
	}

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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	};
	
	
	
}
