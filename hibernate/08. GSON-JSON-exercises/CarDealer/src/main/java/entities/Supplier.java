package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

	@Column
	private String name;
	
	@Column
	private boolean isImporter;
	
	@OneToMany(targetEntity = Part.class, mappedBy = "supplier")
	private Set <Part> parts;

	public Supplier() {
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
