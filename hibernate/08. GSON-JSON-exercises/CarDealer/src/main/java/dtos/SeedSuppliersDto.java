package dtos;

import java.util.HashSet;
import java.util.Set;


import entities.Part;

public class SeedSuppliersDto {

	private String name;
	private boolean isImporter;
	private Set <Part> parts;

	public SeedSuppliersDto() {
		this.setParts(new HashSet<Part>());
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isImporter() {
		return isImporter;
	}

	public void setImporter(boolean isImporter) {
		this.isImporter = isImporter;
	}


	public Set <Part> getParts() {
		return parts;
	}


	public void setParts(Set <Part> parts) {
		this.parts = parts;
	}
}
