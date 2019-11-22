package XMLParseCarDealer.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

	@Column
	private String make;
	
	@Column
	private String model;
	
	@Column(name = "travelled_distance")
	private String travelledDistance;
	

	@ManyToMany(targetEntity = Part.class, fetch = FetchType.EAGER)
	@JoinTable(name = "parts_cars", 
	joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id")) 
	private List<Part> parts;
	
	
	public Car(){
		this.setParts(new ArrayList<Part>());
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTravelledDistance() {
		return travelledDistance;
	}

	public void setTravelledDistance(String travelledDistance) {
		this.travelledDistance = travelledDistance;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	};
	
	
	
}
