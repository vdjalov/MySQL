package XMLParseCarDealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import XMLParseCarDealer.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>{

}
