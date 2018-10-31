package devlight.edu.conference.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Marks;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.MarksRepository;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.service.UserServiceImpl;
import javassist.NotFoundException;

@RestController
@RequestMapping("/jury/")
public class JuryController {
	@Autowired
	MarksRepository marksRepository;
	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	ApplicationService applicationService;

	@PostMapping("mark")
	public void createMark(@RequestBody @Valid Marks mark, Principal principal) throws NotFoundException {
		List<Marks> marksFromDB = marksRepository.findAllByApplicationId(mark.getApplicationId());
		int juryId = userServiceImpl.getUserByUsername(principal.getName()).getId();
		if (marksFromDB != null && marksFromDB.stream().map(DBMark -> DBMark.getJuryId() == juryId).count() == 0 || marksFromDB == null) {
			mark.setJuryId(juryId);
			marksRepository.save(mark);
			Application application = applicationService.getApplicationById(mark.getApplicationId());
			application.setAvarage_mark(marksRepository.getAverageMark(mark.getApplicationId()).get());
			applicationService.editApplication(application);
		}
	}

	@DeleteMapping("mark/{id}")
	public void deleteMark(@PathVariable("id") int id, Principal principal) throws NotFoundException {
		int juryId = userServiceImpl.getUserByUsername(principal.getName()).getId();
		Marks markFromDB = marksRepository.getOne(id);
		if (markFromDB.getJuryId() == juryId) {
			marksRepository.delete(markFromDB);
			Application application = applicationService.getApplicationById(markFromDB.getApplicationId());
			Optional<Double> mark = marksRepository.getAverageMark(markFromDB.getApplicationId());
			if (mark.isPresent())
				application.setAvarage_mark(mark.get());
			else
				application.setAvarage_mark(0);
			applicationService.editApplication(application);

		}
	}

	@PutMapping("user")
	public void editUserAccount(@Valid @RequestBody User user, Principal principal) throws NotFoundException {
		if (user.getUsername().equals(principal.getName())) {
			userServiceImpl.editUser(user);
		} else
			throw new IllegalArgumentException("You can't edit another user's password");
	}

}
