package XMLParseCarDealer.dtos.supplierDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "allSuppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class MultipleLocalSuppliersCountDto {

	@XmlElement(name = "supplier")
	private List<LocalSuppliersCountDto> localSuppliers;

	
	public MultipleLocalSuppliersCountDto(){
		this.localSuppliers = new ArrayList<LocalSuppliersCountDto>();
	}
	
	public List<LocalSuppliersCountDto> getLocalSuppliers() {
		return localSuppliers;
	}

	public void setLocalSuppliers(List<LocalSuppliersCountDto> localSuppliers) {
		this.localSuppliers = localSuppliers;
	}
	
}
