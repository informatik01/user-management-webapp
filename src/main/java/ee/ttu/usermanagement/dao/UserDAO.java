package ee.ttu.usermanagement.dao;

import ee.ttu.usermanagement.entity.User;

public interface UserDAO extends GenericDAO<User, Long> {

	User findUserByEmail(String email);
	
	int deleteUserWithEmail(String email);
	
}
