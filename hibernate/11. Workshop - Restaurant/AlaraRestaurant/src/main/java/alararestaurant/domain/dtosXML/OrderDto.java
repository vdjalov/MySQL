package alararestaurant.domain.dtosXML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto {

	@XmlElement(name = "order")
	private List<BaseOrderDto> allOrders;

	public OrderDto() {
		this.allOrders = new ArrayList<BaseOrderDto>();
	}
	
	
	
	public List<BaseOrderDto> getAllOrders() {
		return allOrders;
	}

	public void setAllOrders(List<BaseOrderDto> allOrders) {
		this.allOrders = allOrders;
	}
	
}
