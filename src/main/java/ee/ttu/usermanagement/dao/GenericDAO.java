package ee.ttu.usermanagement.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, ID extends Serializable> {

	T findById(ID id);
	
	List<T> findAll();
	
	T save(T entity);
	
	void delete(T entity);
	
}
