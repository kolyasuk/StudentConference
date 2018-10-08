package devlight.edu.conference.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.utils.EmailSender;

@RestController
@RequestMapping("/guest/application/")
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	EmailSender emailSender;

	@GetMapping(value = "{id}")
	public Application getApplication(@PathVariable("id") int id) {
		return applicationService.getApplicationById(id);
	}

	@GetMapping
	public List<Application> getApplicationList() {
		return applicationService.getAllApplications();
	}

	@PostMapping
	public void newApplication(@Valid @RequestBody Application application) {
		if (applicationService.addApplication(application) != null) {
			try {
				emailSender.sendEmail("Hi, your application added", "Bingo!", application.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
