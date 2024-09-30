package in.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.main.entity.User;
import in.main.repository.UserRepository;

@Service
public class CustomUserServiceDetails implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByName(username);
		if(user == null) {
			throw new UsernameNotFoundException("user is not found");
		}
		return new CustomUserService(user);
	}

}
