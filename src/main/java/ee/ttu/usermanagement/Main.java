package ee.ttu.usermanagement;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.ttu.usermanagement.entity.User;
import ee.ttu.usermanagement.util.HibernateUtil;

/**
 * This class is meant for quick testing.
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		User john = new User("John", "Dow", "john.dow@gmail.com");
		john.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1980-05-25"));
		User ivan = new User("Ivan", "Ivanov", "ivan.ivanov@mail.ru");
		ivan.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-27"));
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(john);
		session.save(ivan);
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<User> result = session.createQuery("from User").list();
		for (User user : result) {
			System.out.println(user + ", date of birth: " + user.getBirthDate());
		}
        session.getTransaction().commit();
        sessionFactory.close();
        
	}
	
}
