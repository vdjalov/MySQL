package XMLParseCarDealer.dtos.customerDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "all-sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesByCustomerWrapped {

	@XmlElement(name = "sale")
	private List<SaleByCustomer> allSales;

	
	public SalesByCustomerWrapped(){
		this.allSales = new ArrayList<SaleByCustomer>();
	}
	
	public List<SaleByCustomer> getAllSales() {
		return allSales;
	}

	public void setAllSales(List<SaleByCustomer> allSales) {
		this.allSales = allSales;
	}
}
