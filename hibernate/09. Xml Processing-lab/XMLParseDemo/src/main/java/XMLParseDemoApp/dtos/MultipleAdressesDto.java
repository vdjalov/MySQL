package XMLParseDemoApp.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import XMLParseDemoApp.entities.Address;



@XmlRootElement(name = "addresses")
@XmlAccessorType(XmlAccessType.FIELD)
public class MultipleAdressesDto {

	@XmlElement
	private List<Address> addresses;
	
	public MultipleAdressesDto(){
		this.setAddresses(new ArrayList<Address>());
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	};
}
