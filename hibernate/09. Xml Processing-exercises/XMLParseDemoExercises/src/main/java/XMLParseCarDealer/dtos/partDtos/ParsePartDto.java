package XMLParseCarDealer.dtos.partDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParsePartDto {


	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "price")
	private double price;
	
	@XmlAttribute(name = "quantity")
	private String quantity;


	public ParsePartDto(){};
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
