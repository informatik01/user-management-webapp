package ee.ttu.usermanagement.dao;

import java.util.List;

import ee.ttu.usermanagement.entity.UserProfile;

public interface UserDAO extends GenericDAO<UserProfile, Long> {

	UserProfile findByIdEagerly(Long id);
	
	List<UserProfile> findAllEagerly();
	
	UserProfile findUserByEmail(String email);
	
	UserProfile findUserByEmailEagerly(String email);
	
	int deleteUserWithEmail(String email);
	
}
