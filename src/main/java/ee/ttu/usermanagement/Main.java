package ee.ttu.usermanagement;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.util.HibernateUtil;

/**
 * This class is meant for quick testing.
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		UserProfile john = new UserProfile("John", "Dow", "john.dow@gmail.com");
		john.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1980-05-25"));
		UserProfile ivan = new UserProfile("Ivan", "Ivanov", "ivan.ivanov@mail.ru");
		ivan.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-27"));
		UserProfile afanasij = new UserProfile("Afanasij", "Borschov", "afonja@mail.ru");
		afanasij.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1950-09-25"));
		UserProfile anton = new UserProfile("Anton", "Antonov", "anton.antonov@mail.ru");
		anton.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1982-12-27"));
		UserProfile sergej = new UserProfile("Sergej", "Sergeev", "sergej.sergejev@mail.ru");
		sergej.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1981-11-25"));
		UserProfile vitalij = new UserProfile("Vitalij", "Orlov", "vitalij.orlov@gmail.com");
		vitalij.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1960-07-05"));
		UserProfile andrej = new UserProfile("Andrej", "Andreev", "andrej.andreev@gmail.com");
		andrej.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1987-01-26"));
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(john);
		session.save(ivan);
		session.save(afanasij);
		session.save(anton);
		session.save(sergej);
		session.save(vitalij);
		session.save(andrej);
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<UserProfile> result = session.createQuery("from User").list();
		for (UserProfile user : result) {
			System.out.println(user + ", date of birth: " + user.getBirthDate());
		}
        session.getTransaction().commit();
        sessionFactory.close();
        
	}
	
}
