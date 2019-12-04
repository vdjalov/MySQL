package alararestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import alararestaurant.domain.entities.OrderItem;



public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
}
