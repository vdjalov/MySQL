package dtos;

import java.util.HashSet;
import java.util.Set;


import entities.User;

public class SeedUsersDto {

	private String firstName;
	private String lastName;
	private int age;
	private Set<User> friends;
	
	public SeedUsersDto(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.setFriends(new HashSet<User>());
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Set<User> getFriends() {
		return friends;
	}


	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return String.format("%s%n%s%n%d%n", this.firstName, this.lastName, this.age);
	}


	
}
