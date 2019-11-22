package XMLParseCarDealer.dtos.carsAndPartsDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartDto {

	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "price")
	private double price;
	

	public String getName() {
		return name;
	}
	
	public PartDto(){};

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
