package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
