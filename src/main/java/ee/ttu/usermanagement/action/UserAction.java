package ee.ttu.usermanagement.action;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import ee.ttu.usermanagement.entity.Role;
import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.service.UserManagementService;
import ee.ttu.usermanagement.util.HibernateUtil;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserAction.class);

	@Inject
	private UserManagementService userService;

	private long userId;

	private UserProfile user;

	private List<UserProfile> users;

	private List<Role> roles;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String manageUsers() {
		users = userService.getAllUsers();

		return SUCCESS;
	}

	public String addUser() {
		Set<Role> newRoles = new HashSet<Role>();
		for (Role role : roles) {
			if (!role.getName().trim().isEmpty()) {
				newRoles.add(role);
			}
		}
		user.setRoles(newRoles);

		UserProfile userWithSameEmail = userService.findUserByEmail(user.getEmail());
		if (userWithSameEmail != null) {
			addActionError(getText("error.exist.user.email", new String[] { user.getEmail() }));
			return ERROR;
		}

		boolean saved = userService.saveUser(user);
		if (saved) {
			addActionMessage(getText("success.save.user", new String[] { user.toString() }));
		} else {
			addActionError(getText("error.save.user", new String[] { user.toString() }));
			return ERROR;
		}

		return SUCCESS;
	}

	public String showUpdateUser() {
		user = userService.findUserById(userId);

		return SUCCESS;
	}

	//FIXME resolve update duplicate Role names issue
	public String updateUser() {
		UserProfile userWithSameEmail = userService.findUserByEmail(user.getEmail());
		if (userWithSameEmail != null && !userWithSameEmail.getId().equals(user.getId())) {
			addActionError(getText("error.exist.user.email", new String[] { user.getEmail() }));
			return ERROR;
		}

		// Preserving existing and/or updated roles
		Set<Role> newRoles = new HashSet<Role>();
		for (Role role : roles) {
			if (!role.getName().trim().isEmpty()) {
				newRoles.add(role);
			}
		}
		user.setRoles(newRoles);

		boolean saved = userService.saveUser(user);
		if (saved) {
			addActionMessage(getText("success.update.user"));
		} else {
			addActionError(getText("error.update.user", new String[] { user.toString() }));
			return ERROR;
		}

		return SUCCESS;
	}

	public String deleteUser() {
		int deletedNumber = userService.deleteUserWithId(userId);

		if (deletedNumber > 0) {
			addActionMessage(getText("success.delete.user", new String[] { Long.toString(userId) }));
		} else {
			addActionError(getText("error.delete.user"));
			return ERROR;
		}

		return SUCCESS;
	}

	public String execute() throws ParseException {

		return SUCCESS;
	}

}
