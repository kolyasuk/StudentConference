package devlight.edu.conference.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.User;
import devlight.edu.conference.service.UserServiceImpl;

@RestController
public class LoginController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public void createNewUser(@Valid @RequestBody User user) throws Exception {
		Optional<User> oUser = userServiceImpl.getUserByUsernameRegistration(user.getUsername());
		if (oUser.isPresent()) {
			throw new IllegalArgumentException("User is already exist");
		}
		userServiceImpl.addUser(user);
	}

}
