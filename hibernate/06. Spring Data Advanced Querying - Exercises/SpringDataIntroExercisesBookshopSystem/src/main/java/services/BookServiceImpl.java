package services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import entities.Book;
import repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	
	public void registerBook(Book book) {
		this.bookRepository.saveAndFlush(book);
	}

	public List<Book> findAll(){
		return this.bookRepository.findAll();
	}

	@Override
	public List<Book> findByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String authorFirstName,
			String auhtorLastName) {
		return this.bookRepository.findByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc
				(authorFirstName, auhtorLastName);
	}

	@Override
	public List<String> findByAgeRestrictionManualyInputFromTheUser(String ageRestriction) {
		return this.bookRepository.findAllByAgeRestriction(ageRestriction);
	}

	@Override
	public List<String> findTitlesOfAllGoldBooksWithCopiesLessThan5000() {
		return this.bookRepository.findAllGoldBooksWithCopiesLessThan5000();
	}

	@Override
	public List<Object[]> findAllBooksWherePriceIsLessThanFiveOrMoreThanForty() {
		return this.bookRepository.findAllBooksWherPriceIsLessThanFiveOrHigherThan40();
	}

	@Override
	public List<String> findAllBooksThatAreNotPrintedInAParticularYear(int year) {
		return this.bookRepository.findAllBookTitilesThatYearIsNot(year);
	}

	@Override
	public List<Object[]> findAllBooksThatHaveAReleaseDateBeforeAnInputDate(String inputDate) {
		return this.bookRepository.findAllBooksReleaseBeforeAconcreteDate(inputDate);
	}

	@Override
	public List<Book> findAllBooksThatContainACertainStringRegardlessOfChanges(String text) {
		return this.bookRepository.findByTitleContaining(text);
	}

	@Override
	public List<String> findAllBookTitlesWhereAuthorsLastNameStartsWithAcertainString(String endString) {
		return this.bookRepository.findTitlesByAuthorLastNameStartingWith(endString);
	}

	@Override
	public List<Object[]> findTotalCopiesByAuthor() {
		return this.bookRepository.findTotalBookCopiesByAuthorOrderDesc();
	}

	@Override
	public Book findByAConcreteTitle(String title) {
		return this.bookRepository.findByTitleEquals(title);
	}

	@Override
	public List<Book> findAllBooksReleasedAfterAConcreteDate(String date) {
		return this.bookRepository.findByReleaseDateIsAfter(date);
	}

	@Override
	public void updateAllBooksCopiesIssuedAfterAConcreteDate(String date, int copiesToAdd) {
		 this.bookRepository.updateBooks(date, copiesToAdd);
	}

	@Override
	public String getNumberOfBooksUnderCertainNumberOfCopies(int numberOfCopies) {
		return this.bookRepository.getCountOfBooksThatAreUnderCertainAmountOfCopies(numberOfCopies);
	}

	@Override
	public void removeBooksUnderCertainAmountOfCopies(int copiesAmount) {
		 this.bookRepository.removeBooksBelowConcreteAmountOfCopies(copiesAmount);
	}

	

	
	
	
}


