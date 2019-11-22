package XMLParseCarDealer.dtos.carDtos;

import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import XMLParseCarDealer.entities.Part;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParseCarDto {
	
	@XmlElement(name = "make")
	private String make;
	
	@XmlElement(name = "model")
	private String model;
	
	@XmlElement(name = "travelled-distance")
	private String travelledDistance;
	
	
	private List<Part> parts;
	
	public ParseCarDto(){
		this.parts = new ArrayList<Part>();
	}

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

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	
	
	
}
