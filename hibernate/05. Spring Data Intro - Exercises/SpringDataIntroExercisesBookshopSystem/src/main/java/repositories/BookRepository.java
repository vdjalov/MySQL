package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findAll();
	List<Book> findByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc
						(String authorFirstName, String auhtorLastName);
}
