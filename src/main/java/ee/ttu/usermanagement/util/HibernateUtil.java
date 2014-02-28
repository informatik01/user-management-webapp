package ee.ttu.usermanagement.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;

public class HibernateUtil {
	
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.configure().buildSessionFactory(serviceRegistry);
		} catch (Throwable e) {
			LOGGER.error("Error creating SessionFactory");
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
