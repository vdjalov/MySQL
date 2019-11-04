package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "select * from users as u order by rand() limit 1;", nativeQuery = true)
	public User getARandomUser();
}
