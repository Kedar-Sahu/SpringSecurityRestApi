package in.main.service;

import in.main.entity.User;

public interface UserService {

	User save(User user);
	String login(User user);
}
