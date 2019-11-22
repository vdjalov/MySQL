package XMLParseCarDealer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

	@Column
	private String name;
	
	@Column(name = "is_importer")
	private boolean isImporter;
	
	public Supplier(){}

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
	};
	
	
	
}
