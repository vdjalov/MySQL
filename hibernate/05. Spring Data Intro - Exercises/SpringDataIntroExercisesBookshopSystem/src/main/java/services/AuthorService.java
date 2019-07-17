package services;


import java.util.List;

import entities.Author;

public interface AuthorService {

	
	List<Object[]> findAuthorsByNumberOfBooksOrderedDesc();
	List<Object[]> findAuthorsByGivenName();
	void registerAuthor(Author author);
	List<Author>findAll();
}
