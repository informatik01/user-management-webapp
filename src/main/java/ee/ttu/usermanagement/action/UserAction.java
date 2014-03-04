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
	
	private UserProfile user;
	
	private List<UserProfile> users;
	
	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
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
		if (user == null) {
			addActionMessage(getText("error.supply.user"));
			return INPUT;
		}
		
		boolean saved = userService.saveUser(user);
		if (saved) {
			addActionMessage(getText("success.save.user", new String[]{user.toString()}));
		} else {
			addActionError(getText("error.save.user", new String[]{user.toString()}));
			return ERROR;
		}
		user = null;
		
		return SUCCESS;
	}
	
	public String showUpdateUser() {
		user = userService.findUserById(userId);
		
		return SUCCESS;
	}
	
	public String updateUser() {
		boolean saved = userService.saveUser(user);
		if (saved) {
			addActionMessage(getText("success.update.user"));
		} else {
			addActionError(getText("error.update.user", new String[]{user.toString()}));
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String deleteUser() {
		int deletedNumber = userService.deleteUserWithId(userId);
		
		if (deletedNumber > 0) {
			addActionMessage(getText("success.delete.user"));
		} else {
			addActionError(getText("error.delete.user"));
			return ERROR;
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
		setUser(foundUSer);
		
		return SUCCESS;
	}
	
}
