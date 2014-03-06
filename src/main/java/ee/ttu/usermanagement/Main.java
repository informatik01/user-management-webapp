package ee.ttu.usermanagement;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.ttu.usermanagement.entity.Role;
import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.util.HibernateUtil;

/**
 * This class is meant for quick testing.
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		Role auditorRole = new Role();
		auditorRole.setName("ROLE_AUDITOR");
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		
		Set<Role> fullRights = new HashSet<Role>();
		fullRights.add(adminRole);
		fullRights.add(auditorRole);
		
		Set<Role> userRights = new HashSet<Role>();
		userRights.add(userRole);
		
		Set<Role> auditorRights = new HashSet<Role>();
		auditorRights.add(auditorRole);
		
		UserProfile john = new UserProfile("John", "Dow", "john.dow@gmail.com");
		john.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1980-05-25"));
		john.setRoles(auditorRights);
		
		UserProfile ivan = new UserProfile("Ivan", "Ivanov", "ivan.ivanov@mail.ru");
		ivan.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-27"));
		ivan.setRoles(fullRights);
		
		UserProfile afanasij = new UserProfile("Afanasij", "Borschov", "afonja@mail.ru");
		afanasij.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1950-09-25"));
		afanasij.setRoles(userRights);
		
		UserProfile anton = new UserProfile("Anton", "Antonov", "anton.antonov@mail.ru");
		anton.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1982-12-27"));
		
		UserProfile sergej = new UserProfile("Sergej", "Sergeev", "sergej.sergejev@mail.ru");
		sergej.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1981-11-25"));
		sergej.setRoles(fullRights);
		
		UserProfile vitalij = new UserProfile("Vitalij", "Orlov", "vitalij.orlov@gmail.com");
		vitalij.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1960-07-05"));
		
		UserProfile andrej = new UserProfile("Andrej", "Andreev", "andrej.andreev@gmail.com");
		andrej.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1987-01-26"));
		andrej.setRoles(auditorRights);
		
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
		List<UserProfile> result = session.createQuery("from UserProfile").list();
		for (UserProfile userProfile : result) {
			System.out.println(userRole + ", date of birth: " + userProfile.getBirthDate());
		}
        session.getTransaction().commit();
        sessionFactory.close();
        
	}
	
}
