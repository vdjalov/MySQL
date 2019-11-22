package XMLParseCarDealer.dtos.carDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sale-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleInfo {

	@XmlElement(name = "car-info")
	private CarInfo carInfo;
	
	@XmlElement(name = "customer-name")
	private String customerName;
	
	@XmlElement(name = "discount")
	private double discount;
	
	@XmlElement(name = "price")
	private double price;
	
	@XmlElement(name = "price-with-discount")
	private double priceWithDiscount;
	
	
	public SaleInfo(){}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getPriceWithDiscount() {
		return priceWithDiscount;
	}


	public void setPriceWithDiscount(double priceWithDiscount) {
		this.priceWithDiscount = priceWithDiscount;
	}


	public CarInfo getCarInfo() {
		return carInfo;
	}


	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	
	
}
