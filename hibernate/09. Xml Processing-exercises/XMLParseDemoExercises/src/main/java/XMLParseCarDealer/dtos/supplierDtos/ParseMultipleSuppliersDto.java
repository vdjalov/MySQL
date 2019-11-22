package XMLParseCarDealer.dtos.supplierDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParseMultipleSuppliersDto {

	@XmlElement(name = "supplier")
	private List<ParseSupplierDto> allSuppliers;

	
	public ParseMultipleSuppliersDto() {
		this.allSuppliers = new ArrayList<ParseSupplierDto>();
	}
	
	public List<ParseSupplierDto> getAllSuppliers() {
		return allSuppliers;
	}
	
	public void setAllSuppliers(List<ParseSupplierDto> allSuppliers) {
		this.allSuppliers = allSuppliers;
	}
	
}
