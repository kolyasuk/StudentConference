package devlight.edu.conference.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	ApplicationService applicationService;

	@DeleteMapping
	public void deleteUserAccount(Principal principal) throws NotFoundException {
		userServiceImpl.deleteUser(userServiceImpl.getUserByUsername(principal.getName()).get().getId());
	}

	@PutMapping
	public void editUserAccount(@Valid @RequestBody User user, Principal principal) {
		if (user.getUsername().equals(principal.getName())) {
			userServiceImpl.updateUser(user);
		}
	}

	@DeleteMapping(value = "application/{id}")
	public void deleteApplication(@PathVariable("id") int id, Principal principal) throws NotFoundException {
		if (applicationService.getApplicationById(id).getAuthor_id() == userServiceImpl.getUserByUsername(principal.getName()).get().getId()) {
			applicationService.deleteApplication(id);
		}
	}

	@PutMapping(value = "application")
	public void editApplication(@Valid @RequestBody Application application, Principal principal) throws NotFoundException {
		if (application.getAuthor_id() == userServiceImpl.getUserByUsername(principal.getName()).get().getId()) {
			applicationService.updateApplication(application);
		}
	}

}
