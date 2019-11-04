package services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.User;
import repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User user) {
		this.userRepository.saveAndFlush(user);
	}

	public User findRandomUser() {
		return this.userRepository.getARandomUser();
	}

}
