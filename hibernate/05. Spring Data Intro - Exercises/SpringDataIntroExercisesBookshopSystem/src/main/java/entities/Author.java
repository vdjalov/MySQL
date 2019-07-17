package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.bytebuddy.asm.Advice.This;

@Entity
@Table(name = "authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private long authorId;
	
	@Column(name = "first_name")
	private String firstName;
	
	
	@Column(name = "last_name", columnDefinition = "varchar(255) not null ")
	private String lastName;
	
	@OneToMany(targetEntity = Book.class, mappedBy = "author")
	private Set<Book> books;

	public Author(){
		this.books = new HashSet<Book>();
	};
	
	
	public long getAuthorId() {
		return authorId;
	}
	
	public void setAuthoId(long authorId) {
		this.authorId = authorId;
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


	public Set<Book> getBooks() {
		return books;
	}



	
}

