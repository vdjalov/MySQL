package XMLParseCarDealer.dtos.supplierDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParseSupplierDto {

	@XmlAttribute(name = "is-importer")
	private boolean isImporter;
	
	@XmlAttribute(name = "name")
	private String name;
	
	
	public ParseSupplierDto(){}


	public boolean isImporter() {
		return isImporter;
	}


	public void setImporter(boolean isImporter) {
		this.isImporter = isImporter;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	};
	
}
