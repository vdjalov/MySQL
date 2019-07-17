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
	
	
}


