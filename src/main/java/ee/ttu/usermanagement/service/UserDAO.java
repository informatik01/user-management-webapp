package ee.ttu.usermanagement.service;

import ee.ttu.usermanagement.entity.User;

public interface UserDAO extends GenericDAO<User, Long> {

	User findUserByEmail(String email);
	
}
