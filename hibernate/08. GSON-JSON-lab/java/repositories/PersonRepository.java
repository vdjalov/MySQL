package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entities.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT p FROM Person as p JOIN p.address AS a WHERE a.country = :country")
    List<Person> findByCountry(@Param(value = "country") String country);
}
