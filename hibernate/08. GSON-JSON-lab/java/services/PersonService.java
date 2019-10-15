package services;

import java.util.List;

import entities.Person;

public interface PersonService {

	public List<Person> findAllPeopleThatLiveInASpecificCountry(String country);
	public void saveAndFlush(Person person);
}
