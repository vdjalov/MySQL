package services;

import java.util.List;

import entities.Book;

public interface BookService {

	List<Book> findByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc
							(String authorFirstName, String auhtorLastName);
	void registerBook(Book book);
	List<Book> findAll();
}
