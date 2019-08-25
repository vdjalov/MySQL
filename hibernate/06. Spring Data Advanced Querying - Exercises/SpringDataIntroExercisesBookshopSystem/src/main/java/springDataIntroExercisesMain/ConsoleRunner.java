package springDataIntroExercisesMain;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mysql.cj.xdevapi.Result;

import entities.Author;
import entities.Book;
import entities.Category;
import enums.AgeRestriction;
import enums.EditionType;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import services.AuthorService;
import services.BookService;
import services.CategoryService;



@Component
public class ConsoleRunner implements CommandLineRunner {
	private BufferedReader bf;
	
	public ConsoleRunner() {
		this.bf = new BufferedReader(new InputStreamReader(System.in));
	}
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;
	
	
	public void run(String... args) throws Exception {
		// OLD EXERCISES - HEAD 05(previous head)
		//seedDatabase();
		//getAllBooksAfter2000();
		//getAllAuthoerssWithOneBookReleasedBefore1990();
		//getAllAuthorsByNumerOfTheirBooksDesc();
		//findBooksThatAuthorNameIsGeorgePowell();
		
		//NEW EXERCISE - HEAD 06(current head upgrades previous head)
		//findAllByAgeRestrictionInput();
		//getAllGoldEditionBookTitlesWhereCopiesLessThan5000();
		//getAllBooksWherePriceIsLessThanFiveOrMoreThanFifty();
		//getAllBooksThatAreNotPrintedInAParticularYear();
		//getAllBooksPrintedBeforeACertainDate();
	    //getAllAuthorsThatFirstNameEndsWithACertainString();
		//getAllBooksTitlesContainingAConcreteText();	
		//getAllTitlesWhichAuthorStartsWith();
		//getNumberOfBooksWhichTitleIsLongerThanAgivenNumber();
		//getTotalCopiesByAuthorInDescOrder();
		//findBookWithASpecificTitle();
		//increaseBookCopies();
		removeBooks();
		
	}

	//13. *Remove Books
	private void removeBooks() throws NumberFormatException, IOException {
		System.out.println("Please enter number of copies:");
		int numberOfCopies = Integer.valueOf(this.bf.readLine());
		
		String books = this.bookService.getNumberOfBooksUnderCertainNumberOfCopies(numberOfCopies);
		this.bookService.removeBooksUnderCertainAmountOfCopies(numberOfCopies);
			System.out.println("Number of books deleted: " + books);
	}


	// 12. *Increase Book Copies
	private void increaseBookCopies() throws IOException {
		System.out.println("Please enter a date:");
		String[] date = this.bf.readLine().split("[ ]+");
		String finalDate = date[2] + "-" + verifyMonth(date[1]) + "-" + date[0];
		
		System.out.println("Please enter a number of copies to be added:");
		int copiesToAdd = Integer.valueOf(this.bf.readLine());
	
		// Finding all the books numbers
			List<Book> allBooks = this.bookService.findAllBooksReleasedAfterAConcreteDate(finalDate);				
						
			if(allBooks.size() > 0) {
				this.bookService.updateAllBooksCopiesIssuedAfterAConcreteDate(finalDate, copiesToAdd);
			}
		
		int numberOfBooks = allBooks.size();
		int numberOfCopies = numberOfBooks * copiesToAdd;
		System.out.println(numberOfBooks + " books are released after 12 Oct 2005, so total of " + numberOfCopies + " book copies were added" );
	}



	


	// 11.Reduced Book
	private void findBookWithASpecificTitle() throws IOException {
		System.out.println("Enter a book title:");
		String title = this.bf.readLine();
		
		Book book = this.bookService.findByAConcreteTitle(title);
		if(book != null) {
			System.out.println(book.getTitle() + " " + book.getEditionType() + 
					" " + book.getAgeRestriction() + " " + book.getPrice());
		} 
	}


	// 10. Total Book Copies
	private void getTotalCopiesByAuthorInDescOrder() {
		List<Object[]> allAuthors = this.bookService.findTotalCopiesByAuthor();
		
		for(Object[] author : allAuthors) {
			System.out.println(author[0] + " " + author[1] + " - " + author[2]);
		}
	}


	// 9. Count Books
	private void getNumberOfBooksWhichTitleIsLongerThanAgivenNumber() throws NumberFormatException, IOException {
		System.out.println("Please enter a number:");
		int length = Integer.valueOf(this.bf.readLine());
		AtomicInteger aInteger = new AtomicInteger();
		aInteger.set(0);
		
		this.bookService.findAll()
			.stream()
			.filter(book -> book.getTitle().length() > length)
			.forEach(book -> aInteger.getAndIncrement());
		
		System.out.println("There are " + aInteger + " books with longer title than " + length + " symbols");
	}

	// 8. Book Titles Search
	private void getAllTitlesWhichAuthorStartsWith() throws IOException {
		System.out.println("Please enter a final string:");
			String endString = bf.readLine();
			
			this.bookService.findAllBookTitlesWhereAuthorsLastNameStartsWithAcertainString(endString)
				.forEach(title-> {
					System.out.println(title);
				});
			
			
	}

	// 7. Books Search
	private void getAllBooksTitlesContainingAConcreteText() throws IOException {
		System.out.println("Please enter a text:");
		String text = this.bf.readLine();
		
		this.bookService.findAllBooksThatContainACertainStringRegardlessOfChanges(text)
			.forEach(b -> {
				System.out.println(b.getTitle());
			});
			
			
	}


	// 6. Authors Search
	private void getAllAuthorsThatFirstNameEndsWithACertainString() throws IOException {
		System.out.println("Please enter an authro first name last letters:");
		String endString = this.bf.readLine();
		
		this.authorService.findAll().stream()
			.filter(authorService -> authorService.getFirstName().endsWith(endString))
			.forEach(a -> {
				System.out.println(a.getFirstName() + " " + a.getLastName());
			});
		
	}



	// 5. Books Released Before Date
	private void getAllBooksPrintedBeforeACertainDate() throws ParseException, IOException {
		System.out.println("Please enter a date:");
		String inputDate[] = bf.readLine().split("[-]");
		String finalDate = inputDate[2] + "-" + inputDate[1] + "-" + inputDate[0];	
		List<Object[]> books = this.bookService.findAllBooksThatHaveAReleaseDateBeforeAnInputDate(finalDate);
		
		for(Object[]book: books) {
			System.out.println(book[0] + " " + book[1] + " " + book[2]);
		}
	}




	// 4. Not Released Books
	private void getAllBooksThatAreNotPrintedInAParticularYear() throws NumberFormatException, IOException {
		System.out.println("Please enter a year:");
		int year = Integer.valueOf(this.bf.readLine());
		this.bookService.findAllBooksThatAreNotPrintedInAParticularYear(year)
			.forEach(title -> System.out.println(title));
		
	}


	// 3. Books by Price
	private void getAllBooksWherePriceIsLessThanFiveOrMoreThanFifty() {
		List<Object[]> allBooks = this.bookService.findAllBooksWherePriceIsLessThanFiveOrMoreThanForty();
		for(Object[] book: allBooks) {
			System.out.println(book[0] + " - $" + book[1]);
		}	
	}


	//2. Golden Books
	private void getAllGoldEditionBookTitlesWhereCopiesLessThan5000() {
		this.bookService.findTitlesOfAllGoldBooksWithCopiesLessThan5000()
			.forEach(bookTitle -> {
				System.out.println(bookTitle);
			});
		
	}

	// 1. Books Titles by Age Restriction
	private void findAllByAgeRestrictionInput() throws IOException {
		
		List<String> validAgeRestrictions = new ArrayList<>();
		Collections.addAll(validAgeRestrictions, "minor", "adult", "teen");
		
		System.out.println("Please enter a valid age restriction:");
		String ageRestriction = this.bf.readLine().toLowerCase();
		
			if(validAgeRestrictions.contains(ageRestriction)) {
				this.bookService.findByAgeRestrictionManualyInputFromTheUser(ageRestriction)
					.forEach(title -> System.out.println(title));
			} else {
				System.out.println("Age Restriction does not exist!");
			}
		
		
	}


	// Find all books written by George Powell
	private void findBooksThatAuthorNameIsGeorgePowell() {
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
	
	private String verifyMonth(String month) {
		String finalMonth = "";
		
		switch(month) {
		case "Jan":
			finalMonth = "01";
			break;
		case "Feb":
			finalMonth = "02";	
			break;
		case "Mar":
			finalMonth = "03";
			break;
		case "Apr":
			finalMonth = "04";
			break;
		case "May":
			finalMonth = "05";
			break;
		case "Jun":
			finalMonth = "06";
			break;
		case "Jul":
			finalMonth = "07";
			break;
		case "Aug":
			finalMonth = "08";
			break;
		case "Sep":
			finalMonth = "09";
			break;
		case "Oct":
			finalMonth = "10";
			break;
		case "Nov":
			finalMonth = "11";
			break;
		case "Dec":
			finalMonth = "12";
			break;
		}
		return finalMonth;
	}
}
