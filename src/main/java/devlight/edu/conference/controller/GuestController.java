package devlight.edu.conference.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.service.GuestService;
import devlight.edu.conference.validation.CustomFileValidator;

@RestController
@RequestMapping("/guest/")
public class GuestController {

	@Autowired
	CustomFileValidator customFileValidator;

	@Autowired
	GuestService guestService;

	@InitBinder
	public void initBinderFile(WebDataBinder binder) {
		if (customFileValidator.supports(binder.getTarget().getClass()))
			binder.addValidators(customFileValidator);
	}

	@PostMapping("application")
	public void createApplication(@ModelAttribute @Validated FileUpload fileUpload, @ModelAttribute @Valid Application application) throws Exception {
		guestService.createApplication(fileUpload, application);
	}

}
