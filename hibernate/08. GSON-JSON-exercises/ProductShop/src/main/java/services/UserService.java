package services;

import entities.User;

public interface UserService {

	public void saveUser(User user);
	public User findRandomUser(); 
}
