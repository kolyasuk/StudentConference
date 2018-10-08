package devlight.edu.conference.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.User;
import devlight.edu.conference.service.UserServiceImpl;
import javassist.NotFoundException;

@RestController
public class LoginController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public void createNewUser(@Valid @RequestBody User user) throws Exception {
		Optional<User> oUser = userServiceImpl.getUserByUsername(user.getUsername());
		if (oUser.isPresent()) {
			throw new Exception("User is already exist");
		}
		userServiceImpl.addUser(user);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public User home() throws NotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getAuthorities());
		Optional<User> user = userServiceImpl.getUserByUsername(auth.getName());
		return user.get();
	}

}
