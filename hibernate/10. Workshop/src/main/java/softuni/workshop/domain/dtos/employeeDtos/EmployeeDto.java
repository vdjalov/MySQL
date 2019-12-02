package softuni.workshop.domain.dtos.employeeDtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import softuni.workshop.domain.dtos.projectDtos.ProjectDto;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDto {

	@XmlElement(name = "age")
	private int age;
	
	@XmlElement(name = "first-name")
	private String firstName;
	
	@XmlElement(name = "last-name")
	private String lastName;
	
	@XmlElement(name = "project")
	private ProjectDto projectDto;

	
	public EmployeeDto() {}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public ProjectDto getProject() {
		return projectDto;
	}


	public void setProject(ProjectDto project) {
		this.projectDto = project;
	}
	
	
	
	
}
