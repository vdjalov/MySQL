package entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import enums.AgeRestriction;
import enums.EditionType;



@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private long bookId;
	
	@Column(columnDefinition = "varchar(255) not null")
	private String title; 
	
	@Column()
	private String description;
	
	@Column(name= "edition_type")
	@Enumerated(EnumType.STRING)
	private EditionType editionType;
	
	@Column(columnDefinition = "double not null")
	private double price;
	
	@Column(columnDefinition = "bigint not null")
	private int copies;
	
	@Column(name = "release_date") 
	private Date releaseDate;
	
	@Column(name = "age_restriction")
	@Enumerated(EnumType.STRING)
	private AgeRestriction ageRestriction;
	
	
	@ManyToOne(targetEntity = Author.class, optional = false)
	@JoinColumn(name = "author_id", referencedColumnName = "author_id")
	private Author author;

	@ManyToMany(targetEntity= Category.class)
	@JoinTable(name = "books_categories", 
		joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
	private List<Category> categories;

	public Book(){
		this.categories = new ArrayList<Category>();
	};
	
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EditionType getEditionType() {
		return editionType;
	}

	public void setEditionType(EditionType editionType) {
		this.editionType = editionType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public AgeRestriction getAgeRestriction() {
		return ageRestriction;
	}

	public void setAgeRestriction(AgeRestriction ageRestriction) {
		this.ageRestriction = ageRestriction;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Category> getCategories() {
		return categories;
	}

	
}










