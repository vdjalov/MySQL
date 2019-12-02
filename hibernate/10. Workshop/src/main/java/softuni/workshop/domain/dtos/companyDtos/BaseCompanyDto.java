package softuni.workshop.domain.dtos.companyDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseCompanyDto {
	
	@XmlElement(name = "company")
	private List<CompanyDto> allCompanies;

	public BaseCompanyDto() {
		this.allCompanies = new ArrayList<CompanyDto>();
	}
	public List<CompanyDto> getAllCompanies() {
		return allCompanies;
	}

	public void setAllCompanies(List<CompanyDto> allCompanies) {
		this.allCompanies = allCompanies;
	}

}
