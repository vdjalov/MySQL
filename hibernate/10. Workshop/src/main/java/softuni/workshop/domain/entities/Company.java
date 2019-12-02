package softuni.workshop.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.hibernate.validator.constraints.Length;





@Entity
@Table(name = "companies")
public class Company {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@Pattern(regexp = "[\\W\\w]{3,}", message = "Min length of the name should be three(3) letters!")
	private String name;
	
	public Company(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
}
