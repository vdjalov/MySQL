package console;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dtos.PersonDto;
import entities.Person;
import services.PersonService;

@Component
public class ConsoleRun implements CommandLineRunner {

	
	@Autowired
	private PersonService personService;
	
	private ModelMapper modelMapper;
	
	private ConsoleRun() {
		this.modelMapper = new ModelMapper();
	}
	
	public void run(String... args) throws Exception {	
		personToJson();
		JsonToObject();
		
	}

	// JSON to object
	private void JsonToObject() {
		String jsonPerson = "{\"firstName\": \"Victor\",\"lastName\": \"Iugo\",\"addressStreet\": \"Misho Mishev 12\"}";
		Gson gson = new GsonBuilder().create();
		PersonDto pDto = gson.fromJson(jsonPerson, PersonDto.class);
		
		Person person = modelMapper.map(pDto, Person.class);
		this.personService.saveAndFlush(person);
	}

	
	// Person to JSON
	private void personToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		this.personService.findAllPeopleThatLiveInASpecificCountry("Bulgaria")
			.forEach(person -> {
				PersonDto personDto = this.modelMapper.map(person, PersonDto.class);
				System.out.println(gson.toJson(personDto));
			});		
	}
	
}
