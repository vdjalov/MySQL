package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{

}
