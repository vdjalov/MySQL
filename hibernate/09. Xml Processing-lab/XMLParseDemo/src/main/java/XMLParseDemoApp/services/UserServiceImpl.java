package XMLParseDemoApp.services;

import org.springframework.beans.factory.annotation.Autowired;

import XMLParseDemoApp.entities.User;
import XMLParseDemoApp.repositories.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
		public void saveUser(User user) {
		this.userRepository.saveAndFlush(user);
	}

}
