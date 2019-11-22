package XMLParseCarDealer.dtos.carDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParseMultipleCarsDto {

	@XmlElement(name = "car")
	private List<ParseCarDto> allCars;
	
	public ParseMultipleCarsDto() {
		this.setAllCars(new ArrayList<ParseCarDto>());
	}

	
	
	public List<ParseCarDto> getAllCars() {
		return allCars;
	}

	public void setAllCars(List<ParseCarDto> allCars) {
		this.allCars = allCars;
	}
	
	
}
