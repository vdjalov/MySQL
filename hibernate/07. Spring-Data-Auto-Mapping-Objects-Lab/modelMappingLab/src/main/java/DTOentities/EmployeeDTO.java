package DTOentities;

public class EmployeeDTO {

	private String firstName;
	private String lastName;
	private double salary;
	
	
	
	
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
	
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(this.firstName).append(" ")
			    .append(this.lastName).append(" ")
			    .append(this.salary);
		
		return sBuilder.toString();
	}
	
}
