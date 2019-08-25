package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	@Query(value = "select a.first_name, a.last_name," +
				"(select count(b.author_id)" +
				"from books as b\n" +
				"where a.author_id = b.author_id\n" +
				"group by b.author_id\n" + 
				") as number_of_books\n" +
				"from authors as a\n" + 
				"order by number_of_books desc;"
			, nativeQuery = true)
	List<Object[]> findAuthorsByNumberOfBooksOrderedDesc();
	
	@Query(value = 
			"select b.title, b.release_date, b.copies\n" +  
			"from books as b\n" +
			"where (select concat(a.first_name, ' ', a.last_name)\n" +
			"from authors as a\n" +
			"where b.author_id = a.author_id ) = 'George Powell' order by b.release_date desc, b.title asc"
			, nativeQuery = true)
	List<Object[]> findAuthorsByGivenName();

	
}
