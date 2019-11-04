package entities;



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

	@Column(name = "part_name")
	private String name;
	
	@Column
	private double price;
	
	@Column
	private long quantity;
	
	@ManyToMany(targetEntity = Car.class, mappedBy = "parts")
	private Set<Car> cars;

	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private Supplier supplier;
	
	public Part() {
		this.cars = new HashSet<Car>();
	}
	
	
	public String getPartName() {
		return name;
	}

	public void setPartName(String partName) {
		this.name = partName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}
