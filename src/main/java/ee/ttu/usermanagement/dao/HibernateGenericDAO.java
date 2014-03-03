package ee.ttu.usermanagement.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ee.ttu.usermanagement.util.HibernateUtil;

public abstract class HibernateGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	
	public HibernateGenericDAO() {}

	public HibernateGenericDAO(Class<T> c) {
		persistentClass = c;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		T entity = (T) session.get(persistentClass, id);
		
		return entity;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> list = null;
		Session session = HibernateUtil.getSession();
		Query queryResult = session.createQuery("from " + persistentClass.getName());
		list = queryResult.list();
		
		return list;
	}

	@Override
	public T save(T entity) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(entity);
		
		return entity;
	}
	
	@Override
	public void delete(T entity) {
		HibernateUtil.getSession().delete(entity);
	}
	
	public int delete(ID id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("DELETE FROM " + persistentClass.getName() + " WHERE id = :id");
		query.setParameter("id", id);
		int deletedNumber = query.executeUpdate();
		
		return deletedNumber;
	}

}
