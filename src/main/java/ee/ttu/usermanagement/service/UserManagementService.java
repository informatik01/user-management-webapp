package ee.ttu.usermanagement.service;

import java.util.List;

import ee.ttu.usermanagement.entity.User;

public interface UserManagementService {

	void saveUser(User user);
	
	User findUserById(long id);
	
	User findUserByEmail(String email);
	
	List<User> getAllUsers();
	
	void deleteUser(User user);
	
	int deleteUserWithId(long id);
	
	int deleteUserWithEmail(String email);
	
}
