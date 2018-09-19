package devlight.edu.conference.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.User;
import devlight.edu.conference.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@PathVariable("id") int id) {
		return userServiceImpl.getUserById(id);
	}

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userServiceImpl.getAllUsers();
	}

	@PostMapping(value = "/user")
	public void addUser(@Valid @RequestBody User user) {
		userServiceImpl.addUser(user);
	}

	@DeleteMapping(value = "/user/{id}")
	public void deleteUserById(int id) {
		userServiceImpl.deleteUser(id);
	}

	@PutMapping(value = "/user")
	public void editUser(@Valid @RequestBody User user) {
		userServiceImpl.updateUser(user);
	}

}
