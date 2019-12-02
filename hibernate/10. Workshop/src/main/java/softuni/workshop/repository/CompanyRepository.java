package softuni.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import softuni.workshop.domain.dtos.companyDtos.CompanyDto;
import softuni.workshop.domain.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	
}
