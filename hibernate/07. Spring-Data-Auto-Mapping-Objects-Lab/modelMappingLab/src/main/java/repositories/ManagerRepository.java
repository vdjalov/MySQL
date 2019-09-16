package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long>{

}
