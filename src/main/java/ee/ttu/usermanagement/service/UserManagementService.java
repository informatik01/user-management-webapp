package ee.ttu.usermanagement.service;

import java.util.List;

import ee.ttu.usermanagement.entity.UserProfile;

public interface UserManagementService {

	boolean saveUser(UserProfile user);
	
	UserProfile findUserById(long id);
	
	UserProfile findUserByEmail(String email);
	
	List<UserProfile> getAllUsers();
	
	boolean deleteUser(UserProfile user);
	
	int deleteUserWithId(long id);
	
	int deleteUserWithEmail(String email);
	
}
