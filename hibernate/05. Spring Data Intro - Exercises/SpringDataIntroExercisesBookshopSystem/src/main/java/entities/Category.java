package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "cetegories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private long categoryId;
	
	@Column(columnDefinition = "varchar(255) not null")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private Set <Book> books;

	
	public Category(){
		this.books = new HashSet<Book>();
	}
	public long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Set <Book> getBooks() {
		return books;
	}
	

	
	
}
