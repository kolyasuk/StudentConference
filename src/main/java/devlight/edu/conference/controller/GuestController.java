package devlight.edu.conference.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.service.GuestServiceImpl;
import devlight.edu.conference.validation.CustomFileValidator;

@RestController
@RequestMapping("/guest/")
public class GuestController {

	private final CustomFileValidator customFileValidator;
	private final GuestServiceImpl guestService;

	public GuestController(CustomFileValidator customFileValidator, GuestServiceImpl guestService) {
		this.customFileValidator = customFileValidator;
		this.guestService = guestService;
	}

	@InitBinder
	public void initBinderFile(WebDataBinder binder) {
		if (customFileValidator.supports(binder.getTarget().getClass()))
			binder.addValidators(customFileValidator);
	}

	@PostMapping("application")
	public Application createApplication(@ModelAttribute @Validated FileUpload fileUpload, @ModelAttribute @Valid Application application) throws Exception {
		return guestService.createApplication(fileUpload, application);
	}

}
