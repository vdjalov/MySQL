package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long>{
	List<Label> findBySubtitle(String subtitle);
	
	
	

}
