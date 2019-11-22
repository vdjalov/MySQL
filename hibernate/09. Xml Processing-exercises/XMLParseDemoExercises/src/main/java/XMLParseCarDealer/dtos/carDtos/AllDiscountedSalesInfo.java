package XMLParseCarDealer.dtos.carDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "sales-discounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllDiscountedSalesInfo {

	@XmlElement(name = "sale-info")
	private List<SaleInfo> sales;

	
	public AllDiscountedSalesInfo(){
		this.sales = new ArrayList<SaleInfo>();
	}
	
	public List<SaleInfo> getSales() {
		return sales;
	}

	public void setSales(List<SaleInfo> sales) {
		this.sales = sales;
	}
}
