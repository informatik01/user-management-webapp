package ee.ttu.usermanagement.dao;

import ee.ttu.usermanagement.entity.Role;

public interface RoleDAO extends GenericDAO<Role, Long> {
	
	Role findRoleByName(String name);
	
	int deleteRoleWithName(String name);
	
}
