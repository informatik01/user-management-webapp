package ee.ttu.usermanagement.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import ee.ttu.usermanagement.dao.UserDAO;
import ee.ttu.usermanagement.entity.User;
import ee.ttu.usermanagement.util.HibernateUtil;

@Repository
public class DaoUserManagementService implements UserManagementService {

	@Inject
	private UserDAO userDao;
	
	@Override
	public void saveUser(User user) {
		HibernateUtil.beginTransaction();
		userDao.save(user);
		HibernateUtil.commit();
	}

	@Override
	public User findUserById(long id) {
		HibernateUtil.beginTransaction();
		User user = userDao.findById(id);
		HibernateUtil.commit();
		
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		HibernateUtil.beginTransaction();
		User user = userDao.findUserByEmail(email);
		HibernateUtil.commit();
		
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		HibernateUtil.beginTransaction();
		List<User> users = userDao.findAll();
		HibernateUtil.commit();

		return users;
	}

	@Override
	public void deleteUser(User user) {
		HibernateUtil.beginTransaction();
		userDao.delete(user);
		HibernateUtil.commit();
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
