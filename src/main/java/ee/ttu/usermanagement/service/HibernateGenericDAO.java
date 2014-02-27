package ee.ttu.usermanagement.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ee.ttu.usermanagement.util.HibernateUtil;

public abstract class HibernateGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private Class<T> persistentClass;

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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query queryResult = session.createQuery("from User");
		
		return queryResult.list();
	}

	@Override
	public T save(T entity) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(entity);
		
		return entity;
	}

	@Override
	public void delete(T entity) {
		HibernateUtil.getSessionFactory().getCurrentSession().delete(entity);
	}

}
