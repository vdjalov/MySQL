package XMLParseDemoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import XMLParseDemoApp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
