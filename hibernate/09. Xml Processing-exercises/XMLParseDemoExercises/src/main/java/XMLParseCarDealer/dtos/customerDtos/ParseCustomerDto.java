package XMLParseCarDealer.dtos.customerDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParseCustomerDto {


	@XmlAttribute(name = "name")
	private String name;
	
	@XmlElement(name = "birth-date")
	private String birthDate;
	
	@XmlElement(name = "is-young-driver")
	private boolean isYoungDriver;
	
	public ParseCustomerDto(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isYoungDriver() {
		return isYoungDriver;
	}

	public void setYoungDriver(boolean isYoungDriver) {
		this.isYoungDriver = isYoungDriver;
	}
	
	
}
