package repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.mapping.Value;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findAll();
	List<Book> findByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc
						(String authorFirstName, String auhtorLastName);
	
	@Query(value = "select b.title\n" +  
				   "from books as b\n" +  
				   "where b.age_restriction = :ageRestriction", nativeQuery = true)
	List<String>findAllByAgeRestriction(@Param("ageRestriction") String ageRestriction);
	
	
	@Query(value = "select b.title\n" + 
				  "from books as b\n" + 
				  "where b.edition_type = 'GOLD' and b.copies < 5000;\n", nativeQuery = true)
	List<String>findAllGoldBooksWithCopiesLessThan5000();
	
	
	@Query(value ="select b.title, b.price\n" +
			      "from books as b\n" +
			      "where (b.price < 5 or b.price > 40);", nativeQuery = true)
	List<Object[]>findAllBooksWherPriceIsLessThanFiveOrHigherThan40();
	
	@Query(value = "select b.title\n" +  
		  "from books as b\n" +   
		  "where year(b.release_date) <> :year", nativeQuery = true )	
	List<String> findAllBookTitilesThatYearIsNot(@Param("year") int year );
	
	@Query(value = "select b.title, b.edition_type, b.price\n" +
				   "from books as b\n" +
				   "where b.release_date < :date", nativeQuery = true)
	List<Object[]>findAllBooksReleaseBeforeAconcreteDate(@Param("date") String date);

	List<Book> findByTitleContaining(String text);
	
	@Query(value = "select b.title\n" +
				   "from books as b\n" +
				   "join authors as a on b.author_id = a.author_id\n" +
				   "where a.last_name LIKE :endString%", nativeQuery = true)
	List<String>findTitlesByAuthorLastNameStartingWith(@Param("endString") String endString); 
	
	
	@Query(value = "select a.first_name, a.last_name, sum(b.copies) as copies\n" + 
				   "from books as b\n" +
				   "join authors as a on b.author_id = a.author_id\n" +
				   "group by b.author_id\n" +
				   "order by copies desc", nativeQuery = true)
	List<Object[]> findTotalBookCopiesByAuthorOrderDesc();
	
	Book findByTitleEquals(String title);
	
	@Transactional
	@Modifying
	@Query(value = "update books\n" + 
				   "set copies = copies + :addBooks\n" + 
				   "where release_date > :date", nativeQuery = true)
	void updateBooks(@Param("date") String date,@Param("addBooks")int copiesToAdd);
	
	
	@Query(value = "select *\n" +
				   "from books as b\n" + 
				   "where b.release_date > :date", nativeQuery = true)
	List<Book> findByReleaseDateIsAfter(@Param("date") String date);
	
	
	@Query(value = "select count(b.title) as count\n" +
				   "from books as b\n" + 
				   "where b.copies < :copiesNumber", nativeQuery = true)
	String getCountOfBooksThatAreUnderCertainAmountOfCopies(@Param("copiesNumber") int copiesAmount);
	
	@Transactional
	@Modifying
	@Query(value = "delete from books where copies < :copiesNumber", nativeQuery = true)
	void removeBooksBelowConcreteAmountOfCopies(@Param("copiesNumber") int copiesAmount);
	
}








