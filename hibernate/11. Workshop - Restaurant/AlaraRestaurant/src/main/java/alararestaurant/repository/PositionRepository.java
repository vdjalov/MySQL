package alararestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import alararestaurant.domain.entities.Position;

public interface PositionRepository extends JpaRepository<Position, Integer>{
	
	Position findByName(String name);
	
}
