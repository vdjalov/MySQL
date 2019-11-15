package XMLParseDemoApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "addresses")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address extends BaseEntity {

	
	@XmlAttribute
	@Column
	private String country;
	
	@XmlElement
	@Column
	private String city;

	public Address(){};
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
