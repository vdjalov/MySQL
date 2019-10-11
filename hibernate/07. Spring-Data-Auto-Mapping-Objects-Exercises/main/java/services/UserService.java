package services;

import entities.User;

public interface UserService {
	public void saveUser(User user);
	public int findUsersSize();
	public boolean findIfEmailIsAlreadyRegistered(String email);
	public User findByEmail(String email);
}
