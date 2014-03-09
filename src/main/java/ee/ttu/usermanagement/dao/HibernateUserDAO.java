package ee.ttu.usermanagement.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.util.HibernateUtil;

@Repository
public class HibernateUserDAO extends HibernateGenericDAO<UserProfile, Long> implements UserDAO {

	public HibernateUserDAO() {
		super(UserProfile.class);
	}

	@Override
	public UserProfile findByIdEagerly(Long id) {
		UserProfile user = findById(id);
		Hibernate.initialize(user.getRoles());
		Hibernate.initialize(user.getCars());
		
		return user;
	}
	
	@Override
	public List<UserProfile> findAllEagerly() {
		List<UserProfile> users = findAll();
		for (UserProfile user : users) {
			Hibernate.initialize(user.getRoles());
			Hibernate.initialize(user.getCars());
		}
		
		return users;
	}
	
	@Override
	public UserProfile findUserByEmail(String email) {
		UserProfile user = null;
		String sql = "SELECT u FROM UserProfile AS u WHERE u.email = :email";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql).setParameter("email", email);
		user = (UserProfile) query.uniqueResult();
		
		return user;
	}

	@Override
	public UserProfile findUserByEmailEagerly(String email) {
		UserProfile user = findUserByEmail(email);
		Hibernate.initialize(user.getRoles());
		Hibernate.initialize(user.getCars());
		
		return user;
	}
	
	@Override
	public int deleteUserWithEmail(String email) {
		String sql = "DELETE FROM UserProfile AS u WHERE u.email = :email";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql).setParameter("email", email);
		int deletedCount = query.executeUpdate();

		return deletedCount;
	}

}
