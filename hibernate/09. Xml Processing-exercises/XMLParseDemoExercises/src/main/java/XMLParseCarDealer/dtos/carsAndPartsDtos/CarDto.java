package XMLParseCarDealer.dtos.carsAndPartsDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarDto {

	@XmlElement(name = "name")
	private String make;
	
	@XmlElement(name = "model")
	private String model;
	
	@XmlElement(name = "travelled-distance")
	private String travelledDistance;
	
	@XmlElement(name = "part")
	private List<PartDto> allParts;

	
	public CarDto(){
		this.allParts = new ArrayList<PartDto>();
	};
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTravelledDistance() {
		return travelledDistance;
	}

	public void setTravelledDistance(String travelledDistance) {
		this.travelledDistance = travelledDistance;
	}

	public List<PartDto> getAllParts() {
		return allParts;
	}

	public void setAllParts(List<PartDto> allParts) {
		this.allParts = allParts;
	}
	
	
	
	
	
	
}
