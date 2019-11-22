package XMLParseCarDealer.dtos.carDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarInfo {
	
	@XmlElement(name = "make")
	private String make;
	
	@XmlElement(name = "model")
	private String model;
	
	@XmlElement(name = "travelled-distance")
	private String travelledDistance;
	
	
	public CarInfo(){}


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
	};
	
	

}
