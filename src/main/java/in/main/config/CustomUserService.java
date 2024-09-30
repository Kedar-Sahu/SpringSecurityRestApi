package in.main.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.main.entity.User;

public class CustomUserService implements UserDetails {

	private User user;
	
	public CustomUserService(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority("USER");
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
		 
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

}
