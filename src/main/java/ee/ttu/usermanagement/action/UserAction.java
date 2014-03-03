package ee.ttu.usermanagement.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.service.UserManagementService;

public class UserAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	
	@Inject
	private UserManagementService userService;

	private long userId;
	
	private UserProfile currentUser;
	
	private List<UserProfile> users;
	
	public UserProfile getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserProfile currentUser) {
		this.currentUser = currentUser;
	}

	public List<UserProfile> getUsers() {
		return users;
	}

	public void setUsers(List<UserProfile> users) {
		this.users = users;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String manageUsers() {
		users = userService.getAllUsers();
		
		return SUCCESS;
	}
	
	public String addUser() {
		userService.saveUser(currentUser);
		currentUser = null;
		
		return SUCCESS;
	}
	
	public String showUpdateUser() {
		currentUser = userService.findUserById(userId);
		
		return SUCCESS;
	}
	
	public String updateUser() {
		userService.saveUser(currentUser);
		
		return SUCCESS;
	}
	
	public String deleteUser() {
		int deletedNumber = userService.deleteUserWithId(userId);
		
		if (deletedNumber > 0) {
			addActionMessage(getText("manage.user.deleted", new String[]{Long.toString(userId)}));
		}
		// We need to get a fresh users list
		users = userService.getAllUsers();
				
		return SUCCESS;
	}
	
	public String execute() throws ParseException {
		String email = "john.dow@gmail.com";
		UserProfile foundUSer = userService.findUserByEmail(email);
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
