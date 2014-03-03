package ee.ttu.usermanagement.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.service.UserManagementService;

public class UserAction extends ActionSupport implements ParameterAware {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	
	@Inject
	private UserManagementService userService;
	
	private UserProfile currentUser;
	
	private List<UserProfile> users;
	
	private Map<String, String[]> parameters;
	
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
		
	}
	
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
		long id = Long.parseLong(getParameters().get("id")[0]);
		System.out.println("***** Update id=" + id);
		currentUser = userService.findUserById(id);
		
		return SUCCESS;
	}
	
	public String updateUser() {
		System.out.println("***** ID = " + currentUser.getId());
		userService.saveUser(currentUser);
		
		return SUCCESS;
	}
	
	public String deleteUser() {
		long id = Long.parseLong(getParameters().get("id")[0]);
		int deletedNumber = userService.deleteUserWithId(id);
		
		System.out.println("Deleted: " + deletedNumber + ", text: " + getText("manage.user.deleted"));
		if (deletedNumber > 0) {
			addActionMessage(getText("manage.user.deleted", new String[]{Long.toString(id)}));
		}
		System.out.println("Action message: " + getActionMessages());
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
