package ee.ttu.usermanagement.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import ee.ttu.usermanagement.dao.UserDAO;
import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.util.HibernateUtil;

@Repository
public class DaoUserManagementService implements UserManagementService {
	
	private static final Logger LOGGER = Logger.getLogger(DaoUserManagementService.class);

	@Inject
	private UserDAO userDao;
	
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
			LOGGER.error("Error saving user with ID " + user.getId());
			HibernateUtil.rollbackTransaction();
			return false;
		}
		
		return true;
	}

	@Override
	public UserProfile findUserById(long id) {
		UserProfile user = null;
		try {
			HibernateUtil.beginTransaction();
			user = userDao.findById(id);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding user with ID " + id);
			HibernateUtil.rollbackTransaction();
			return null;
		}
		
		return user;
	}

	@Override
	public UserProfile findUserByEmail(String email) {
		if (email == null) {
			throw new NullPointerException("Email must not be null ");
		} else if (email.trim().isEmpty()) {
			return null;
		}
		
		UserProfile user = null;
		try {
			HibernateUtil.beginTransaction();
			user = userDao.findUserByEmail(email);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding user with email " + email);
			HibernateUtil.rollbackTransaction();
		}
		
		return user;
	}
	
	@Override
	public List<UserProfile> getAllUsers() {
		List<UserProfile> users = null;
		try {
			HibernateUtil.beginTransaction();
			users = userDao.findAll();
			HibernateUtil.commit();
		} catch (HibernateException e) {
			LOGGER.error("Error finding all users");
			HibernateUtil.rollbackTransaction();
		}

		return users;
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

}
