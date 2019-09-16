package DTOentities;

public class EmployeeDTOProjection {

	private String firstName;
	private String lastName;
	private double salary;
	private String managerLastName;
	
	
	
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getManagerLastName() {
		return managerLastName;
	}
	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}
	
	
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		
		String managerName = "[no manager]";
			if(this.getManagerLastName() != null) {
				managerName = this.getManagerLastName();
			}
		sBuilder.append(this.getFirstName()).append(" ")
				.append(this.getLastName()).append(" ")
				.append(this.getSalary()).append(" - Manager:").append(managerName);
		
		return sBuilder.toString();
	}
	
}
