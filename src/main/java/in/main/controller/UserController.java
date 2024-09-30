package in.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.main.entity.User;
import in.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {

	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<?> home(HttpServletRequest request){
		String id=request.getSession().getId();
		return new ResponseEntity<>("Spring Security JWT Token "+id,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user){
		User saveUser=userService.save(user);
		if(saveUser == null) {
			return new ResponseEntity<>("User Save Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("User Save Successfully",HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		String token=userService.login(user);
		if(token == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(token,HttpStatus.OK);
	}
}
