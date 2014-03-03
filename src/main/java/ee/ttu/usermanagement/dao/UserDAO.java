package ee.ttu.usermanagement.dao;

import ee.ttu.usermanagement.entity.UserProfile;

public interface UserDAO extends GenericDAO<UserProfile, Long> {

	UserProfile findUserByEmail(String email);
	
	int deleteUserWithEmail(String email);
	
}
