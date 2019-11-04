package dtos;

import java.util.HashSet;
import java.util.Set;


import entities.Part;

public class SeedCarsDto {


	private String make;
	private String model;
	private double travelledDistance;
	private Set<Part> parts; 
	
	public SeedCarsDto() {
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

	public void setParts(Set<Part> parts2) {
		this.parts = parts2;
	}
	
}
