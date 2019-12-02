package softuni.workshop.domain.dtos.employeeDtos;

public class EmployeesWithAgeOverTwentyFiveDto {

	private String fullName;
	private String age; 
	private String projectName;
	
	public EmployeesWithAgeOverTwentyFiveDto() {}

	
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	};
	
	public String toString() {
		return String.format("<employee> \n" +  
							 "	<full-name> = %s \n" +
							 "	<age> = %s \n" +
							 "	<project-name> = %s \n" +
						     "<employee>", this.fullName, this.age, this.projectName );
	}
	
}
