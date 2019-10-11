package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.User;
import repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		this.userRepository.saveAndFlush(user);
	}

	@Override
	public int findUsersSize() {
		return this.userRepository.findAll().size();
	}

	@Override
	public boolean findIfEmailIsAlreadyRegistered(String email) {
		if(this.userRepository.findByEmail(email) != null) {
			return true;
		}
		return false;
	}

	@Override
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	

}
