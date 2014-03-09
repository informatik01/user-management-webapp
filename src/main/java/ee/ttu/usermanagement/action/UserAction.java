package ee.ttu.usermanagement.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ee.ttu.usermanagement.entity.Car;
import ee.ttu.usermanagement.entity.Role;
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

	/*
	 * IMPLEMENTATION NOTE
	 * 
	 * We need this roles variable to overcome
	 * the issue with mapping elements of HashSet.
	 * (roles in the UserProfile class are stored as HashSet)
	 */
	private Collection<Role> roles;

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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String manageUsers() {
		users = userService.getAllUsers(true);

		return SUCCESS;
	}

	public String addUser() {
		if (!canSaveOrUpdateUser(false)) {
			return ERROR;
		}

		// If we are here the user already saved,
		// so we do this to clear a new user input fields
		user = null;
		roles = null;

		return SUCCESS;
	}

	public String showUpdateUser() {
		user = userService.findUserById(userId, true);
		roles = user.getRoles();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		// Storing current user data to preserve user's password
		// because we're not going to update it here.
		session.put("userProfile", user);
		
		return SUCCESS;
	}

	public String updateUser() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserProfile currentUser = (UserProfile) session.get("userProfile");
		user.setPassword(currentUser.getPassword());
		
		if (!canSaveOrUpdateUser(true)) {
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

	// Helper method
	private boolean canSaveOrUpdateUser(boolean update) {
		if (roles != null) {
			Set<Role> rolesToAdd = new HashSet<Role>();
			for (Role role : roles) {
				if (!role.getName().trim().isEmpty()) {
					Role existingRole = userService.findRoleByName(role.getName());
					if (existingRole != null) {
						// If the role already exists, set its ID
						// so a role with the same name won't be created.
						role.setId(existingRole.getId());
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("***** Existing role found: " +
										existingRole.getName() +
										" (id=" + existingRole.getId() + ")");
						}
					} else {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("***** New role to add: " + role.getName());
						}
					}
					rolesToAdd.add(role);
				}
			}
			user.setRoles(rolesToAdd);
			// We need this cast, otherwise there will be problems with the HashSet
			// when using it on JSP pages with input fields.
			roles = new ArrayList<Role>(rolesToAdd);
		}

		if (user.getCars() != null) {
			for (Iterator<Car> it = user.getCars().iterator(); it.hasNext();) {
				Car car = it.next();
				if (car.getPlateNumber().trim().isEmpty()) {
					it.remove();
				}
			}
		}

		UserProfile existingUser = userService.findUserByEmail(user.getEmail(), false);
		if (existingUser != null) {
			if (!update) {
				addActionError(getText("error.exist.user.email", new String[] { user.getEmail() }));
				return false;
			} else if (!existingUser.getId().equals(user.getId())) {
				addActionError(getText("error.exist.user.email", new String[] { user.getEmail() }));
				return false;
			}
		}

		boolean saved = userService.saveUser(user);
		if (saved) {
			addActionMessage(getText("success.save.user", new String[] { user.toString() }));
		} else {
			addActionError(getText("error.save.user", new String[] { user.toString() }));

			return false;
		}

		return true;
	}

}
