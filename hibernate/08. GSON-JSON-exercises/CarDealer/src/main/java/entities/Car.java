package entities;

import java.util.HashSet;
import java.util.Set;

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
	
	@Column
	private double travelledDistance;
	
	@ManyToMany(targetEntity = Part.class, fetch = FetchType.EAGER)
	@JoinTable(name = "parts_cars", 
	joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"))
	private Set<Part> parts; 
	

	
	public Car() {
		this.parts = new HashSet<Part>();
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


	public double getTravelledDistance() {
		return travelledDistance;
	}


	public void setTravelledDistance(double travelledDistance) {
		this.travelledDistance = travelledDistance;
	}


	public Set<Part> getParts() {
		return parts;
	}


	public void setParts(HashSet<Part> parts) {
		this.parts = parts;
	}


	
	
	
	
}
