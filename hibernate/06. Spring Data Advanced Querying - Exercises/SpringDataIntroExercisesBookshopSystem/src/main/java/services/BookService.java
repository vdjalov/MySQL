package services;

import java.util.List;

import entities.Book;

public interface BookService {

	List<Book> findByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc
							(String authorFirstName, String auhtorLastName);
	void registerBook(Book book);
	List<Book> findAll();
	List<String> findByAgeRestrictionManualyInputFromTheUser(String ageRestriction);
	List<String>findTitlesOfAllGoldBooksWithCopiesLessThan5000();
	List<Object[]>findAllBooksWherePriceIsLessThanFiveOrMoreThanForty();
	List<String> findAllBooksThatAreNotPrintedInAParticularYear(int year);
	List<Object[]>findAllBooksThatHaveAReleaseDateBeforeAnInputDate(String inputDate);
	List<Book> findAllBooksThatContainACertainStringRegardlessOfChanges(String text);
	List<String> findAllBookTitlesWhereAuthorsLastNameStartsWithAcertainString(String endString);
	List<Object[]> findTotalCopiesByAuthor();
	Book findByAConcreteTitle(String title);
	List<Book> findAllBooksReleasedAfterAConcreteDate(String date);
	void updateAllBooksCopiesIssuedAfterAConcreteDate(String date, int copiesToAdd);
	String getNumberOfBooksUnderCertainNumberOfCopies(int numberOfCopies);
	void removeBooksUnderCertainAmountOfCopies(int copiesAmount);
}
