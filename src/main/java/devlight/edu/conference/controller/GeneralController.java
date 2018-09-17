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

import devlight.edu.conference.model.Application;
import devlight.edu.conference.service.ApplicationService;

@RestController
public class GeneralController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping(value = "/application/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Application getApplication(@PathVariable("id") int id) {
		return applicationService.getApplicationById(id);
	}

	@GetMapping(value = "/application")
	public List<Application> getApplicationList() {
		return applicationService.getAllApplications();
	}

	@PostMapping(value = "/application")
	public void newApplication(@Valid @RequestBody Application application) {
		applicationService.addApplication(application);
	}

	@DeleteMapping(value = "/application/{id}")
	public void deleteApplication(@PathVariable("id") int id) {
		applicationService.deleteApplication(id);
	}

	@PutMapping(value = "/application")
	public void updateApplication(@Valid @RequestBody Application application) {
		applicationService.updateApplication(application);
	}

}
