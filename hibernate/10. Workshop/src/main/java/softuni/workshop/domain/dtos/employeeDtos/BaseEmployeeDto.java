package softuni.workshop.domain.dtos.employeeDtos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseEmployeeDto {

	
	@XmlElement(name = "employee")
	private List<EmployeeDto> allEmployees;

	
	public BaseEmployeeDto() {
		this.allEmployees = new ArrayList<EmployeeDto>();
	}
	
	
	public List<EmployeeDto> getAllEmployees() {
		return allEmployees;
	}

	public void setAllEmployees(List<EmployeeDto> allEmployees) {
		this.allEmployees = allEmployees;
	}
	
	
	
}
