package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

	@Column
	private String name;
	
	@Column
	private String birthDate;
	
	@Column
	private boolean isYoungDriver;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isYoungDriver() {
		return isYoungDriver;
	}
	public void setYoungDriver(boolean isYoungDriver) {
		this.isYoungDriver = isYoungDriver;
	}
	
}
