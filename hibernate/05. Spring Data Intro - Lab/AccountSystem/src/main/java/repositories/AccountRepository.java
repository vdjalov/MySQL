package repositories;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entities.Account;
import entities.User;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{

	Optional<Account> findByUser(Optional<User> optional);
}
