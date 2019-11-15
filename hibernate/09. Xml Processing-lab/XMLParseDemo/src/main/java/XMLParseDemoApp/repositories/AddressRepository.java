package XMLParseDemoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import XMLParseDemoApp.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
