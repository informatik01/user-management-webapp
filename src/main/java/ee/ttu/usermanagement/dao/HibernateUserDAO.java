package ee.ttu.usermanagement.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ee.ttu.usermanagement.entity.User;
import ee.ttu.usermanagement.util.HibernateUtil;

@Repository
public class HibernateUserDAO extends HibernateGenericDAO<User, Long> implements UserDAO {

	public HibernateUserDAO() {
		super(User.class);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = null;
		String sql = "SELECT u FROM User AS u WHERE u.email = :email";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql).setParameter("email", email);
		user = (User) query.uniqueResult();
		
		return user;
	}

	@Override
	public int deleteUserWithEmail(String email) {
		String sql = "DELETE FROM User AS u WHERE u.email = :email";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql);
		int deletedCount = query.executeUpdate();

		return deletedCount;
	}

}
