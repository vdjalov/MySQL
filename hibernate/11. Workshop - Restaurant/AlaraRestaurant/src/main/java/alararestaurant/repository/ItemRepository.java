package alararestaurant.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import alararestaurant.domain.entities.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	Item findByName(String name);
}
