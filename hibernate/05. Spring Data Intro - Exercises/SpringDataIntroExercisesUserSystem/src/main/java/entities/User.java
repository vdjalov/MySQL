package entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import annotations.Password;



@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false) 
	private String username;
	
	@Column(nullable = false)
	@Password(minLength = 6,
    maxLengh = 50,
    containsDigits = true,
    containsLowerCase = true,
    containsUpperCase = true,
    containsSpecialSymbols = true)
	private String password;
	
	@Email
	private String email;
		
	@Column(name = "registered_on")
	private Date date;
	
	@Column(name = "last_time_logged_in")
	private Date lastLog;
	
	@Min(value = 1)
	@Max(value = 120)
	private int age;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	
	
	
	
	@ManyToOne
	private Town bornInTown;
	
	
	@ManyToOne(targetEntity = Town.class)
	private Town currentlyLiving;
	
	@Column(name = "all_friends")
	@ManyToMany()
	@JoinTable(name = "user_friends", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
	private Set<Friend> allFriends;
	
	@OneToMany(targetEntity = Album.class, mappedBy = "user")
	private List<Album> albums;
	
	
	public User(){};
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		if(username.length() < 4 || username.length() > 30) {
			throw new IllegalArgumentException("Username should be between 4 and 30 symbols");
		}
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getLastLog() {
		return lastLog;
	}
	public void setLastLog(Date lastLog) {
		this.lastLog = lastLog;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Town getBornInTown() {
		return bornInTown;
	}
	public void setBornInTown(Town bornInTown) {
		this.bornInTown = bornInTown;
	}
	public Town getCurrentlyLiving() {
		return currentlyLiving;
	}
	public void setCurrentlyLiving(Town currentlyLiving) {
		this.currentlyLiving = currentlyLiving;
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
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	
	
	
	
}
