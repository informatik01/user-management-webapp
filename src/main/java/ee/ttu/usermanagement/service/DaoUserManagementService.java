package ee.ttu.usermanagement.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import ee.ttu.usermanagement.dao.RoleDAO;
import ee.ttu.usermanagement.dao.UserDAO;
import ee.ttu.usermanagement.entity.Role;
import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.util.HibernateUtil;

@Repository
public class DaoUserManagementService implements UserManagementService {
	
	private static final Logger LOGGER = Logger.getLogger(DaoUserManagementService.class);

	@Inject
	private UserDAO userDao;
	
	@Inject 
	private RoleDAO roleDao;
	
	
	@Override
	public boolean saveUser(UserProfile user) {
		if (user == null) {
			throw new NullPointerException("User must not be null");
		}
		
		try {
			HibernateUtil.beginTransaction();
			userDao.save(user);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error saving user with email" + user.getEmail());
			HibernateUtil.rollbackTransaction();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean saveRole(Role role) {
		if (role == null) {
			throw new NullPointerException("Role must not be null");
		}
		
		try {
			HibernateUtil.beginTransaction();
			roleDao.save(role);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error saving role with name " + role.getName());
			HibernateUtil.rollbackTransaction();
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public UserProfile findUserById(long id, boolean eagerly) {
		UserProfile user = null;
		try {
			HibernateUtil.beginTransaction();
			
			if (eagerly) {
				user = userDao.findByIdEagerly(id);
			} else {
				user = userDao.findById(id);
			}
			
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding user with ID " + id);
			HibernateUtil.rollbackTransaction();
			return null;
		}
		
		return user;
	}

	@Override
	public UserProfile findUserByEmail(String email, boolean eagerly) {
		if (email == null) {
			throw new NullPointerException("Email must not be null");
		} else if (email.trim().isEmpty()) {
			return null;
		}
		
		UserProfile user = null;
		try {
			HibernateUtil.beginTransaction();
			
			if (eagerly) {
				user = userDao.findUserByEmailEagerly(email.trim());
			} else {
				user = userDao.findUserByEmail(email.trim());
			}
			
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding user with email " + email);
			HibernateUtil.rollbackTransaction();
		}
		
		return user;
	}
	
	@Override
	public Role findRoleById(long id) {
		Role role = null;
		try {
			HibernateUtil.beginTransaction();
			role = roleDao.findById(id);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding role with ID " + id);
			HibernateUtil.rollbackTransaction();
			return null;
		}
		
		return role;
	}

	@Override
	public Role findRoleByName(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null");
		} else if (name.trim().isEmpty()) {
			return null;
		}
		
		Role role = null;
		try {
			HibernateUtil.beginTransaction();
			role = roleDao.findRoleByName(name.trim());
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding role with name " + name);
			HibernateUtil.rollbackTransaction();
		}
		
		return role;
	}
	
	
	@Override
	public List<UserProfile> getAllUsers(boolean eagerly) {
		List<UserProfile> users = null;
		try {
			HibernateUtil.beginTransaction();
			
			if (eagerly) {
				users = userDao.findAllEagerly();
			} else {
				users = userDao.findAll();
			}
			
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding all users");
			HibernateUtil.rollbackTransaction();
		}

		return users;
	}
	
	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = null;
		try {
			HibernateUtil.beginTransaction();
			roles = roleDao.findAll();
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding all roles");
			HibernateUtil.rollbackTransaction();
		}

		return roles;
	}
	

	@Override
	public boolean deleteUser(UserProfile user) {
		if (user == null) {
			throw new NullPointerException("User must not be null");
		}
		
		try {
			HibernateUtil.beginTransaction();
			userDao.delete(user);			
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error deleting user with id " + user.getId());
			HibernateUtil.rollbackTransaction();
			return false;
		}
		
		return true;
	}

	@Override
	public int deleteUserWithId(long id) {
		int deletedCount = 0;
		try {
			HibernateUtil.beginTransaction();
			deletedCount = userDao.delete(id);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error deleting user with id " + id);
			HibernateUtil.rollbackTransaction();
		}
		
		return deletedCount;
	}

	@Override
	public int deleteUserWithEmail(String email) {
		int deletedCount = 0;
		try {
			HibernateUtil.beginTransaction();
			deletedCount = userDao.deleteUserWithEmail(email);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error deleting user with email " + email);
			HibernateUtil.rollbackTransaction();
		}
		
		return deletedCount;
	}

	@Override
	public boolean deleteRole(Role role) {
		if (role == null) {
			throw new NullPointerException("Role must not be null");
		}
		
		try {
			HibernateUtil.beginTransaction();
			roleDao.delete(role);			
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error deleting role with id " + role.getId());
			HibernateUtil.rollbackTransaction();
			return false;
		}
		
		return true;
	}

	@Override
	public int deleteRoleWithId(long id) {
		int deletedCount = 0;
		try {
			HibernateUtil.beginTransaction();
			deletedCount = roleDao.delete(id);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error deleting role with id " + id);
			HibernateUtil.rollbackTransaction();
		}
		
		return deletedCount;
	}

	@Override
	public int deleteRoleWithName(String name) {
		int deletedCount = 0;
		try {
			HibernateUtil.beginTransaction();
			deletedCount = roleDao.deleteRoleWithName(name);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error deleting role with email " + name);
			HibernateUtil.rollbackTransaction();
		}
		
		return deletedCount;
	}

}
