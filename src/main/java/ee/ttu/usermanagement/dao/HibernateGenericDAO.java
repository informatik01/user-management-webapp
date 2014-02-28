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
		T entity = (T) session.load(persistentClass, id);
		
		return entity;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> list = null;
		Session session = HibernateUtil.getSession();
		// FIXME remove transaction handling from DAO
		session.beginTransaction();
		Query queryResult = session.createQuery("from " + persistentClass.getName());
		list = queryResult.list();
		session.getTransaction().commit();
		
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

}
