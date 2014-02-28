package ee.ttu.usermanagement.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import ee.ttu.usermanagement.dao.UserDAO;
import ee.ttu.usermanagement.entity.User;

public class UserAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	
	@Inject
	private UserDAO userDao;

	private User currentUser;
	
	private List<User> users;
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String manageUsers() {
		users = userDao.findAll();
		
		return SUCCESS;
	}
	
	public String execute() throws ParseException {
		String email = "john.dow@gmail.com";
		User foundUSer = userDao.findUserByEmail(email);
		if (foundUSer != null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("User is found: " + foundUSer);
			}
			foundUSer.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1980-05-25"));
		} else {
			addActionError("User with email " + email + " is NOT found.");
		}
		setCurrentUser(foundUSer);
		
		return SUCCESS;
	}
	
}
