package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Person;
import repositories.PersonRepository;

@Service
public class PersonServiceImplementation implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> findAllPeopleThatLiveInASpecificCountry(String country) {
		return this.personRepository.findByCountry(country);
	}

	@Override
	public void saveAndFlush(Person person) {
		this.personRepository.saveAndFlush(person);
	}

}
