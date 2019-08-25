package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Author;
import repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	public void registerAuthor(Author author) {
		this.authorRepository.saveAndFlush(author);
	}


	public List<Author> findAll() {
		return this.authorRepository.findAll();
	}


	@Override
	public List<Object[]> findAuthorsByNumberOfBooksOrderedDesc() {
		return this.authorRepository.findAuthorsByNumberOfBooksOrderedDesc();
	}


	@Override
	public List<Object[]> findAuthorsByGivenName() {
		return this.authorRepository.findAuthorsByGivenName();
	}


	

	
	

	

}
