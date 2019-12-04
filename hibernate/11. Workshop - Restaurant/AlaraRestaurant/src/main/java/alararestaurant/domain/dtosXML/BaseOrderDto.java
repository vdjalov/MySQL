package alararestaurant.domain.dtosXML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.OrderType;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseOrderDto {

	@XmlElement(name = "customer")
	private String customer;
	
	@XmlElement(name = "date-time")
	private String dateTime;
	
	@XmlElement(name = "type")
	private OrderType orderType;
	
	@XmlElement(name = "items")
	private List<ItemDto> orderItems;
	
	
	@XmlElement(name = "employee")
	private String employee;
	
	public BaseOrderDto() {
		this.orderItems = new ArrayList<ItemDto>();
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public OrderType getOrderType() {
		return orderType;
	}


	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}


	public List<ItemDto> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<ItemDto> orderItems) {
		this.orderItems = orderItems;
	}


	public String getEmployeeName() {
		return employee;
	}


	public void setEmployeeName(String employee) {
		this.employee = employee;
	}



	
	
	
	
}




