package softuni.workshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import softuni.workshop.domain.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

	List<Project> findAllByIsFinishedIsTrue();
	
}
