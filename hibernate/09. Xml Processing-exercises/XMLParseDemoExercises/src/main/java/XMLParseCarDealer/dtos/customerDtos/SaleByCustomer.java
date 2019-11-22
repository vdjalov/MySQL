package XMLParseCarDealer.dtos.customerDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleByCustomer {

	@XmlElement(name = "customer-name")
	private String customerName;

	@XmlElement(name = "bought-cars")
	private int boughtCars;
	
	@XmlElement(name = "spent-money")
	private double spentMoney;
	
	
	public SaleByCustomer(){};
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getBoughtCars() {
		return boughtCars;
	}

	public void setBoughtCars(int boughtCars) {
		this.boughtCars = boughtCars;
	}

	public double getSpentMoney() {
		return spentMoney;
	}

	public void setSpentMoney(double spentMoney) {
		this.spentMoney = spentMoney;
	}
}
