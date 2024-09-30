package in.main.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.main.entity.User;
import in.main.repository.UserRepository;
import in.main.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public User save(User user) {
	    String password=passwordEncoder.encode(user.getPassword());
	    user.setPassword(password);
	    User saveUser= userRepository.save(user);
		return saveUser;
	}

	@Override
	public String login(User user) {
		Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
		if(authenticate.isAuthenticated()) {
			return jwtService.generate(user.getName());
		}
		return null;
	}

}
