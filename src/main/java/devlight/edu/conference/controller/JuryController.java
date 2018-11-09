package devlight.edu.conference.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Marks;
import devlight.edu.conference.model.User;
import devlight.edu.conference.service.JuryServiceImpl;

@RestController
@RequestMapping("/jury/")
public class JuryController {
	private final JuryServiceImpl juryService;

	public JuryController(JuryServiceImpl juryService) {
		super();
		this.juryService = juryService;
	}

	@PostMapping("mark")
	public void createMark(@RequestBody @Valid Marks mark, Principal principal) {
		juryService.createMark(mark, principal.getName());
	}

	@DeleteMapping("mark/{id}")
	public void deleteMark(@PathVariable("id") int markId, Principal principal) {
		juryService.deleteMark(markId, principal.getName());
	}

	@PutMapping("user")
	public void editJuryAccount(@Valid @RequestBody User user, Principal principal) {
		juryService.editJuryAccount(user, principal.getName());
	}

}
