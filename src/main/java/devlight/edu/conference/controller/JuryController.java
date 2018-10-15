package devlight.edu.conference.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Marks;
import devlight.edu.conference.repository.MarksRepository;
import devlight.edu.conference.service.UserServiceImpl;
import javassist.NotFoundException;

@RestController
@RequestMapping("/jury/")
public class JuryController {
	@Autowired
	MarksRepository marksRepository;
	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("mark")
	public void setMark(@RequestBody Marks mark, Principal principal) throws NotFoundException {
		List<Marks> marksFromDB = marksRepository.findAllByApplicationId(mark.getApplicationId());
		int juryId = userServiceImpl.getUserByUsername(principal.getName()).getId();
		if (marksFromDB != null && marksFromDB.stream().map(DBMark -> DBMark.getJuryId() == juryId).count() == 0 || marksFromDB == null) {
			mark.setJuryId(juryId);
			marksRepository.save(mark);
		}
	}

	@PutMapping("mark")
	public void editMark(@RequestBody Marks mark, Principal principal) throws NotFoundException {
		int juryId = userServiceImpl.getUserByUsername(principal.getName()).getId();
		Marks markFromDB = marksRepository.getOne(mark.getId());
		if (markFromDB.getJuryId() == juryId && markFromDB.getApplicationId() == mark.getApplicationId()) {
			marksRepository.save(mark);
		}

	}

	@DeleteMapping("mark/{id}")
	public void deleteMark(@PathVariable("id") int id, Principal principal) throws NotFoundException {
		int juryId = userServiceImpl.getUserByUsername(principal.getName()).getId();
		Marks markFromDB = marksRepository.getOne(id);
		if (markFromDB.getJuryId() == juryId) {
			marksRepository.delete(markFromDB);
		}

	}

}
