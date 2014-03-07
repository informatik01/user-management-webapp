package ee.ttu.usermanagement.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ee.ttu.usermanagement.entity.Role;
import ee.ttu.usermanagement.util.HibernateUtil;

@Repository
public class HibernateRoleDAO extends HibernateGenericDAO<Role, Long> implements RoleDAO {
	
	public HibernateRoleDAO() {
		super(Role.class);
	}

	@Override
	public Role findRoleByName(String name) {
		Role role = null;
		String sql = "SELECT r FROM Role AS r WHERE r.name = :name";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql).setParameter("name", name);
		role = (Role) query.uniqueResult();
		
		return role;
	}

	@Override
	public int deleteRoleWithName(String name) {
		String sql = "DELETE FROM Role AS r WHERE r.name = :name";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql).setParameter("name", name);
		int deletedCount = query.executeUpdate();
		
		return deletedCount;
	}

}
