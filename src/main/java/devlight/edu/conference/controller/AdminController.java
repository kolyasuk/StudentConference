package devlight.edu.conference.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Curator;
import devlight.edu.conference.model.Direction;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.User;
import devlight.edu.conference.service.AdminServiceImpl;
import javassist.NotFoundException;

@RestController
@RequestMapping("/admin/")
public class AdminController {

	private final AdminServiceImpl adminService;

	public AdminController(AdminServiceImpl adminService) {
		this.adminService = adminService;
	}

	@GetMapping(value = "file/{id}")
	public File getFileById(@PathVariable("id") int id) throws NotFoundException {
		return adminService.getFile(id);
	}

	@GetMapping("user/id/{id}")
	public User getUserById(@PathVariable("id") int id) {
		return adminService.getUser(id);
	}

	@GetMapping("user/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return adminService.getUser(username);
	}

	@GetMapping("user")
	public List<User> getAllUsers() throws NotFoundException {
		return adminService.getAllUsers();
	}

	@PostMapping("registration")
	public void createNewUser(@Valid @RequestBody User user) {
		adminService.addUser(user);
	}

	@PostMapping("direction")
	public Direction addDirection(@Valid @RequestBody Direction direction) throws NotFoundException {
		return adminService.addDirection(direction);
	}

	@PostMapping("curator")
	public Curator addCurator(@Valid @RequestBody Curator curator) throws NotFoundException {
		return adminService.addCurator(curator);
	}

	@PutMapping("direction")
	public void editDirection(@Valid @RequestBody Direction direction) {
		adminService.editDirection(direction);
	}

	@PutMapping("curator")
	public void editCurator(@Valid @RequestBody Curator curator) {
		adminService.editCurator(curator);
	}

	@PutMapping("user")
	public void editUser(@Valid @RequestBody User user) throws NotFoundException {
		adminService.editUser(user);
	}

	@PutMapping("application/{id}")
	public void approveApplication(@PathVariable("id") int id, @RequestParam("decision") boolean approveValue) throws Exception {
		adminService.approveApplication(id, approveValue);
	}

	@DeleteMapping("/direction/{id}")
	public void deleteDirection(@PathVariable("id") int id) throws NotFoundException {
		adminService.deleteDirection(id);
	}

	@DeleteMapping("/curator/{id}")
	public void deleteCurator(@PathVariable("id") int id) throws NotFoundException {
		adminService.deleteCurator(id);
	}

	@DeleteMapping("/application/{id}")
	public void deleteApplication(@PathVariable("id") int id) {
		adminService.deleteApplication(id);
	}

	@DeleteMapping("user/{id}")
	public void deleteUserById(int id, Principal principal) throws NotFoundException {
		adminService.deleteUser(id, principal.getName());
	}

}
