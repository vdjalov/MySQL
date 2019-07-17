package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>{

}
