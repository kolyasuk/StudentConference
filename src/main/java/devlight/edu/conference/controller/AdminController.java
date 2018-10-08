package devlight.edu.conference.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.User;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.service.UserServiceImpl;
import javassist.NotFoundException;

@RestController
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	ApplicationService applicationService;

	@GetMapping(value = "user/id/{id}")
	public User getUserById(@PathVariable("id") int id) throws NotFoundException {
		return userServiceImpl.getUserById(id);
	}

	@GetMapping(value = "user/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws NotFoundException {
		return userServiceImpl.getUserByUsername(username).get();
	}

	@GetMapping("user")
	public List<User> getAllUsers(Principal principal) {
		System.out.println(principal.getName());
		return userServiceImpl.getAllUsers();
	}

	@DeleteMapping(value = "user/{id}")
	public void deleteUserById(int id) {
		userServiceImpl.deleteUser(id);
	}

	@PutMapping(value = "user")
	public void editUser(@Valid @RequestBody User user) {
		userServiceImpl.updateUser(user);
	}

	@DeleteMapping(value = "/application/{id}")
	public void deleteApplication(@PathVariable("id") int id) {
		applicationService.deleteApplication(id);
	}

	@PutMapping(value = "/application")
	public void editApplication(@Valid @RequestBody Application application) {
		applicationService.updateApplication(application);
	}

}
