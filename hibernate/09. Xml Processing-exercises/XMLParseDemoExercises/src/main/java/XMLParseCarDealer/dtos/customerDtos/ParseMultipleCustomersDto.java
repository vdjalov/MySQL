package XMLParseCarDealer.dtos.customerDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParseMultipleCustomersDto {

	@XmlElement(name = "customer")
	private List<ParseCustomerDto> allCustomers;

	public ParseMultipleCustomersDto(){
		this.allCustomers = new ArrayList<ParseCustomerDto>();
	}
	
	public List<ParseCustomerDto> getAllCustomers() {
		return allCustomers;
	}

	public void setAllCustomers(List<ParseCustomerDto> allCustomers) {
		this.allCustomers = allCustomers;
	}
}
