package XMLParseCarDealer.dtos.partDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParseMultiplePartsDto {

	@XmlElement(name = "part")
	private List<ParsePartDto> allParts;

	public ParseMultiplePartsDto(){
		this.allParts = new ArrayList<ParsePartDto>();
	};
	
	public List<ParsePartDto> getAllParts() {
		return allParts;
	}

	public void setAllParts(List<ParsePartDto> allParts) {
		this.allParts = allParts;
	}
	
	
}
