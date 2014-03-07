package ee.ttu.usermanagement.security;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ee.ttu.usermanagement.entity.Role;
import ee.ttu.usermanagement.entity.UserProfile;
import ee.ttu.usermanagement.service.UserManagementService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Inject
	private UserManagementService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserProfile userProfile = userService.findUserByEmail(email);
		if (userProfile == null) {
			throw new  UsernameNotFoundException("User with the email " + email + " not found");
		}
		
		// Using email as username
		String username = userProfile.getEmail();
	    String password = userProfile.getPassword();
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	    for (Role role : userProfile.getRoles()) {
	    	authorities.add(new SimpleGrantedAuthority(role.getName()));
	    }
	    
	    // org.springframework.security.core.userdetails.User implements UserDetails
		User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		return user;
	}

}
