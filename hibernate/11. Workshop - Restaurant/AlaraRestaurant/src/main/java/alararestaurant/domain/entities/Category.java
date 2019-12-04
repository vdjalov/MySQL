package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

	@Column(nullable = false)
	@Size(min = 3, max = 30)
	private String name;

	
	public Category(){}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
