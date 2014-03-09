package ee.ttu.usermanagement.service;

import java.util.List;

import ee.ttu.usermanagement.entity.Role;
import ee.ttu.usermanagement.entity.UserProfile;

public interface UserManagementService {

	boolean saveUser(UserProfile user);
	
	boolean saveRole(Role role);
	
	
	UserProfile findUserById(long id, boolean eagerly);
	
	UserProfile findUserByEmail(String email, boolean eagerly);
	
	Role findRoleById(long id);
	
	Role findRoleByName(String name);
	
	
	List<UserProfile> getAllUsers(boolean eagerly);
	
	List<Role> getAllRoles();
	
	
	boolean deleteUser(UserProfile user);
	
	int deleteUserWithId(long id);
	
	int deleteUserWithEmail(String email);
	
	boolean deleteRole(Role role);
	
	int deleteRoleWithId(long id);
	
	int deleteRoleWithName(String name);
	
}
