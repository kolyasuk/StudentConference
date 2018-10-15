package devlight.edu.conference.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Curator;
import devlight.edu.conference.model.Direction;
import devlight.edu.conference.model.Marks;
import devlight.edu.conference.model.Role;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.MarksRepository;
import devlight.edu.conference.repository.RoleRepository;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.service.CuratorServiceImpl;
import devlight.edu.conference.service.DirectionServiceImpl;
import devlight.edu.conference.service.UserServiceImpl;
import devlight.edu.conference.utils.EmailSender;
import javassist.NotFoundException;

@RestController
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	EmailSender emailSender;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	MarksRepository marksRepository;

	@Autowired
	CuratorServiceImpl curatorServiceImpl;

	@Autowired
	DirectionServiceImpl directionServiceImpl;
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("user/id/{id}")
	public User getUserById(@PathVariable("id") int id) throws NotFoundException {
		return userServiceImpl.getUserById(id);
	}

	@GetMapping(value = "user/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws NotFoundException {
		return userServiceImpl.getUserByUsername(username);
	}

	@GetMapping("user")
	public List<User> getAllUsers(Principal principal) throws NotFoundException {
		return userServiceImpl.getAllUsers();
	}

	@PostMapping("direction")
	public Direction addDirection(@Valid @RequestBody Direction direction) throws NotFoundException {
		return directionServiceImpl.addDirection(direction);
	}

	@PostMapping("curator")
	public Curator addCurator(@Valid @RequestBody Curator curator) throws NotFoundException {
		return curatorServiceImpl.addCurator(curator);
	}

	@PutMapping("direction")
	public void editDirection(@Valid @RequestBody Direction direction) {
		directionServiceImpl.editDirection(direction);
	}

	@PutMapping("curator")
	public void editCurator(@Valid @RequestBody Curator curator) {
		curatorServiceImpl.editCurator(curator);
	}

	@PutMapping("user")
	public void editUser(@Valid @RequestBody User user) throws NotFoundException {
		userServiceImpl.editUser(user, true);
	}

	@PutMapping("userRole/{id}")
	public void editUserRoles(@PathVariable("id") int id, @Valid @RequestBody Set<Role> roles) throws NotFoundException {
		User user = userServiceImpl.getUserById(id);
		Set<Role> rolesFromDB = roles.stream().map(rl -> roleRepository.findByRole(rl.getRole())).collect(Collectors.toSet());
		user.setRoles(rolesFromDB);
		userServiceImpl.editUser(user, false);
	}

	@PutMapping("/application")
	public void editApplication(@Valid @RequestBody Application application) {
		applicationService.editApplication(application);
	}

	@PutMapping("/application/{id}")
	public void approveApplication(@PathVariable("id") int id, @RequestParam("approve") boolean approve) throws Exception {
		Application application = applicationService.getApplicationById(id);
		application.setApproved(approve);
		application.setAvarage_mark(marksRepository.getAverageMark(id));
		applicationService.editApplication(application);
		try {
			emailSender.sendEmail("Hi, " + application.getName() + ", your application is" + (approve ? "" : " not") + " approved!", "Congratulation!", application.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@DeleteMapping("/direction/{id}")
	public void deleteDirection(@PathVariable("id") int id) throws NotFoundException {
		directionServiceImpl.deleteDirection(id);
	}

	@DeleteMapping("/curator/{id}")
	public void deleteCurator(@PathVariable("id") int id) throws NotFoundException {
		curatorServiceImpl.deleteCurator(id);
	}

	@DeleteMapping("mark/{id}")
	public void deleteMark(@PathVariable("id") int id) throws NotFoundException {
		Optional<Marks> markFromDB = marksRepository.findById(id);
		if (markFromDB.isPresent()) {
			marksRepository.delete(markFromDB.get());
		}
	}

	@DeleteMapping("/application/{id}")
	public void deleteApplication(@PathVariable("id") int id) throws NotFoundException {
		applicationService.deleteApplication(id);
	}

	@DeleteMapping("user/{id}")
	public void deleteUserById(int id) throws NotFoundException {
		userServiceImpl.deleteUser(id);
	}

}
