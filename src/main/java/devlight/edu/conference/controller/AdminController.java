package devlight.edu.conference.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Curator;
import devlight.edu.conference.model.Direction;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.Role;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.MarksRepository;
import devlight.edu.conference.repository.RoleRepository;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.service.CuratorServiceImpl;
import devlight.edu.conference.service.DirectionServiceImpl;
import devlight.edu.conference.service.FileServiceImpl;
import devlight.edu.conference.service.UserServiceImpl;
import devlight.edu.conference.utils.EmailSender;
import devlight.edu.conference.validation.CustomFileValidator;
import javassist.NotFoundException;

@RestController
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	EmailSender emailSender;

	@Autowired
	FileServiceImpl fileServiceImpl;

	@Autowired
	CustomFileValidator customFileValidator;

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

	@InitBinder("file")
	public void initBinderFile(WebDataBinder binder) {
		binder.addValidators(customFileValidator);
	}

	@GetMapping(value = "file/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public File getFileById(@PathVariable("id") int id) throws NotFoundException {
		return fileServiceImpl.getFileById(id);
	}

	@GetMapping("user/id/{id}")
	public User getUserById(@PathVariable("id") int id) throws NotFoundException {
		return userServiceImpl.getUserById(id);
	}

	@GetMapping(value = "user/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws NotFoundException {
		return userServiceImpl.getUserByUsername(username);
	}

	@GetMapping("user")
	public List<User> getAllUsers() throws NotFoundException {
		return userServiceImpl.getAllUsers();
	}

	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public void createNewUser(@Valid @RequestBody User user) throws Exception {
		Optional<User> oUser = userServiceImpl.getUserByUsernameRegistration(user.getUsername());
		if (oUser.isPresent()) {
			throw new IllegalArgumentException("User is already exist");
		}
		userServiceImpl.addUser(user);
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

	@PutMapping("/verifyApplication/{id}")
	public void approveApplication(@PathVariable("id") int id, @RequestParam("decision") boolean approveValue) throws Exception {
		Application application = applicationService.getApplicationById(id);
		application.setApproved(approveValue);
		applicationService.editApplication(application);
		try {
			emailSender.sendEmail("Hi, " + application.getName() + ", your application is" + (approveValue ? "" : " not") + " approved!", "Congratulation!", application.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@DeleteMapping("file/{id}")
	public void deleteFileById(int id) throws NotFoundException {
		if (fileServiceImpl.getFileById(id) == null)
			fileServiceImpl.deleteFile(id);
	}

	@DeleteMapping("/direction/{id}")
	public void deleteDirection(@PathVariable("id") int id) throws NotFoundException {
		directionServiceImpl.deleteDirection(id);
	}

	@DeleteMapping("/curator/{id}")
	public void deleteCurator(@PathVariable("id") int id) throws NotFoundException {
		curatorServiceImpl.deleteCurator(id);
	}

	@DeleteMapping("/application/{id}")
	public void deleteApplication(@PathVariable("id") int id) throws NotFoundException {
		applicationService.deleteApplication(id);
	}

	@DeleteMapping("user/{id}")
	public void deleteUserById(int id, Principal principal) throws Exception {
		if (userServiceImpl.getUserByUsername(principal.getName()).getId() != id)
			userServiceImpl.deleteUser(id);
		else
			throw new Exception("You can't delete your account");
	}

}
