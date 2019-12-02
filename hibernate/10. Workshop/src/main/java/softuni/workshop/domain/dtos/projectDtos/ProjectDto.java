package softuni.workshop.domain.dtos.projectDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import softuni.workshop.domain.dtos.companyDtos.CompanyDto;


@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDto {

	
	@XmlElement(name = "description")
	private String description;
	
	@XmlElement(name = "is-finished")
	private boolean isFinished;
	
	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "payment")
	private double payment;
	
	@XmlElement(name = "start-date")
	private String startDate;
	
	@XmlElement(name = "company")
	private CompanyDto company;
	
	public ProjectDto() {}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}
	
	
	public String toString() {
		return String.format("<project> \n" + 
						        "	<name>%s</name> \n" + 
						        "	<description>%s</description> \n" +
						        "	<start-date>%s</start-date> \n" + 
						        "	<is-finished>%s</is-finished> \n" + 
						        "	<payment>%.2f</payment> \n" + 
						        "	<company name = Phoenix/> \n" + 
						    "</project> \n" , 
						    	this.name, this.description, this.startDate, this.isFinished, this.payment, this.company.getName());
	}
	
}
