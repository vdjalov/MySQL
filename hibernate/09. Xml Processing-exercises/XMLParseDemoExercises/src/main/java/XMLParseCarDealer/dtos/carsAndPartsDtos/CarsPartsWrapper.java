package XMLParseCarDealer.dtos.carsAndPartsDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars-parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsPartsWrapper {

	@XmlElement(name = "car")
	private List<CarDto> allCars;
	
	
	public CarsPartsWrapper() {
		this.setAllCars(new ArrayList<CarDto>());
	}


	public List<CarDto> getAllCars() {
		return allCars;
	}


	public void setAllCars(List<CarDto> allCars) {
		this.allCars = allCars;
	}
	
}
