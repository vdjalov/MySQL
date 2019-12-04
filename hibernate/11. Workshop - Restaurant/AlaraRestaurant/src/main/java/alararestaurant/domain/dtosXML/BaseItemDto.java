package alararestaurant.domain.dtosXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import alararestaurant.domain.entities.Order;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseItemDto {


	@XmlTransient
	private Order order;
	
	@XmlElement(name = "name")
	private String itemName;
	
	@XmlElement(name = "quantity")
	private int quantity;
	
	
	public BaseItemDto() {}
	
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
