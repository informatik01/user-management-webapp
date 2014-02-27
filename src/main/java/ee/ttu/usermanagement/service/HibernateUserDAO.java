package ee.ttu.usermanagement.service;

import ee.ttu.usermanagement.entity.User;

public class HibernateUserDAO extends HibernateGenericDAO<User, Long> implements UserDAO {

	public HibernateUserDAO() {
		super(User.class);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
