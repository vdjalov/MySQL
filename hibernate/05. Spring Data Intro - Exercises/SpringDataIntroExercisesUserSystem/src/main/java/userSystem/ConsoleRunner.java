package userSystem;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import entities.Town;
import entities.User;
import repositories.TownRepository;
import repositories.UserRepository;

@Component
public class ConsoleRunner implements CommandLineRunner {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TownRepository townRepository;
	
	public void run(String... args) throws Exception {
		
		Town live = new  Town();
		live.setCountry("bangladesh");
		live.setName("OOPS");
		
		this.townRepository.saveAndFlush(live);
		Town born = new Town();
		born.setCountry("bangladesh");
		born.setName("My town");
		this.townRepository.saveAndFlush(born);
		
		User user = new User();
		user.setAge(-1210);
		user.setUsername("Vlad");
		user.setPassword("ok");
		user.setBornInTown(born);
		user.setCurrentlyLiving(live);
		user.setDate(new Date());
		user.setDeleted(false);
		user.setEmail("crap.sdadasd");
		userRepository.saveAndFlush(user);
	}
}
