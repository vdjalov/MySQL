package springDataIntroExercisesMain;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import entities.Author;
import entities.Book;
import entities.Category;
import enums.AgeRestriction;
import enums.EditionType;
import services.AuthorService;
import services.BookService;
import services.CategoryService;



@Component
public class ConsoleRunner implements CommandLineRunner {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;
	
	
	public void run(String... args) throws Exception {
		//seedDatabase();
		//getAllBooksAfter2000();
		//getAllAuthoerssWithOneBookReleasedBefore1990();
		//getAllAuthorsByNumerOfTheirBooksDesc();
		//findBooksThatNameIsGeorgePowell();
	}

	
	// Find all books written by George Powell
	private void findBooksThatNameIsGeorgePowell() {
		List<Object[]> allBooks = this.authorService.findAuthorsByGivenName();
		
			allBooks.stream().forEach(b -> {
				System.out.println(b[0] + " " + b[1] + " " + b[2]);
			});
	}

	// All authors by book number desc
	private void getAllAuthorsByNumerOfTheirBooksDesc() {
		List<Object[]> allAuthors = this.authorService.findAuthorsByNumberOfBooksOrderedDesc();
			for(Object[] aa: allAuthors) {
				System.out.print(aa[0] + " ");
				System.out.print(aa[1] + " ");
				System.out.println(aa[2]);
			}
	}

	// All with at least one book released before 1990
	private void getAllAuthoerssWithOneBookReleasedBefore1990() {
		this.bookService.findAll().stream()
		  .filter(b -> b.getReleaseDate().before(new Date("01/01/1990")))
		  .forEach(b -> {
			  	System.out.println(b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName());
		  });

		
	}

	// Get all the book after the year of 2000
	private void getAllBooksAfter2000() {
		this.bookService.findAll().stream()
 		  .filter(b -> b.getReleaseDate().after(new Date("12/31/2000")))
		  .forEach(b -> {
				System.out.println(b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName());
		  });
		
	}

	private void seedDatabase() throws IOException, ParseException {
		List<Author> allAuthors = new ArrayList<Author>();
		List<Category> allCategories = new ArrayList<Category>();
		List<Book> allBooks = new ArrayList<Book>();
		fillAuthors(allAuthors); // Filling authors 
		fillCategories(allCategories); // Filling categories
		fillBooks(allBooks, allCategories, allAuthors);// Filling books;
	}
	
	private void fillBooks(List<Book> allBooks, List<Category> allCategories,
			List<Author> allAuthors) throws IOException, ParseException {
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader("a:\\books.txt"));
		String line = "";
		Random rnd = new Random();
			while((line = bufferedReader.readLine()) != null) {
				int getRandomCategoryIndex = rnd.nextInt(allCategories.size());
				int getRandomAuthorIndex = rnd.nextInt(allAuthors.size());
			
				Author currentAuthor = allAuthors.get(getRandomAuthorIndex);
				Category currentCategory = allCategories.get(getRandomCategoryIndex);
				Book book = new Book();
				String[] result = line.split("\\s+");
				
				EditionType editionType = EditionType.values()[Integer.valueOf(result[0])];
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy");
				Date releaseDate = simpleDateFormat.parse(result[1]);
				int copies = Integer.valueOf(result[2]);
				double price = Double.valueOf(result[3]);
				AgeRestriction ageRestriction = AgeRestriction.values()[Integer.valueOf(result[4])];
				
				StringBuilder sBuilder = new StringBuilder();
					for(int i = 5; i < result.length; i++) {
						if(i < result.length - 1) {
							sBuilder.append(result[i]).append(" ");
						} else {
							sBuilder.append(result[i]);
						}
						
					}
				
				String title = sBuilder.toString();
				book.setAgeRestriction(ageRestriction);
				book.setCopies(copies);
				book.setEditionType(editionType);
				book.setPrice(price);
				book.setReleaseDate(releaseDate);
				book.setTitle(title);
				book.setAuthor(currentAuthor);
				book.getCategories().add(currentCategory);
				book.getCategories().add(allCategories.get(0));
				bookService.registerBook(book);
				
				
			}
	}

	
	// Filling Categories
	private void fillCategories(List<Category> allCategories) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("A:\\categories.txt"));
			String line = "";
			while((line = bf.readLine()) != null) {
				if(!line.isEmpty()) {
					Category category = new Category();
					category.setName(line);
					this.categoryService.registerCategory(category);
					allCategories.add(category);
				}	
			}
	}

	// Filling the Authors
	private void fillAuthors(List<Author> allAuthors) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("A:\\authors.txt"));
		String line = "";
		while((line = bf.readLine()) != null) {
			String result[] = line.split("\\s+");
			Author author = new Author();
			author.setFirstName(result[0]);
			author.setLastName(result[1]);
			
			this.authorService.registerAuthor(author);
			allAuthors.add(author);
		}
	}
}
