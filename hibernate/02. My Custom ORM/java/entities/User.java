package entities;

import java.util.Date;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

@Entity(name = "users")
public class User {
	
	@Id
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "registration_date")
	private Date registrationDate;
	
	public User(){};
	public User(String username, String password, int age, Date registrationDate) {
		this.setUsername(username);
		this.setPassword(password);
		this.setAge(age);
		this.setRegistrationDate(registrationDate);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	
	
	
	
	
}
