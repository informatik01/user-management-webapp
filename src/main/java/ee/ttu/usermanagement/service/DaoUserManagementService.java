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
	public void saveUser(UserProfile user) {
		HibernateUtil.beginTransaction();
		userDao.save(user);
		HibernateUtil.commit();
	}

	@Override
	public UserProfile findUserById(long id) {
		HibernateUtil.beginTransaction();
		UserProfile user = userDao.findById(id);
		HibernateUtil.commit();
		
		return user;
	}

	@Override
	public UserProfile findUserByEmail(String email) {
		HibernateUtil.beginTransaction();
		UserProfile user = userDao.findUserByEmail(email);
		HibernateUtil.commit();
		
		return user;
	}
	
	@Override
	public List<UserProfile> getAllUsers() {
		HibernateUtil.beginTransaction();
		List<UserProfile> users = userDao.findAll();
		HibernateUtil.commit();

		return users;
	}

	@Override
	public boolean deleteUser(UserProfile user) {
		HibernateUtil.beginTransaction();
		try {
			userDao.delete(user);			
		} catch (HibernateException e) {
			LOGGER.error("Error deleting user with id " + user.getId());
			return false;
		}
		HibernateUtil.commit();
		
		return true;
	}

	@Override
	public int deleteUserWithId(long id) {
		HibernateUtil.beginTransaction();
		int deletedCount = userDao.delete(id);
		HibernateUtil.commit();
		
		return deletedCount;
	}

	@Override
	public int deleteUserWithEmail(String email) {
		HibernateUtil.beginTransaction();
		int deletedCount = userDao.deleteUserWithEmail(email);
		HibernateUtil.commit();
		
		return deletedCount;
	}

}
