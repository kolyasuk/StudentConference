package devlight.edu.conference.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.model.User;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.service.FileServiceImpl;
import devlight.edu.conference.service.UserServiceImpl;
import devlight.edu.conference.utils.EmailSender;
import devlight.edu.conference.validation.CustomFileValidator;
import javassist.NotFoundException;

@RestController
@RequestMapping("/guest/")
public class GuestController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	EmailSender emailSender;

	@Autowired
	FileServiceImpl fileServiceImpl;

	@Autowired
	CustomFileValidator customFileValidator;

	@Autowired
	UserServiceImpl userServiceImpl;

	@InitBinder
	public void initBinderFile(WebDataBinder binder) {
		if (customFileValidator.supports(binder.getTarget().getClass()))
			binder.addValidators(customFileValidator);
	}

	@PostMapping("application")
	public void newApplication(@ModelAttribute @Validated FileUpload fileUpload, @ModelAttribute @Valid Application application) throws NotFoundException, IOException {
		application.setPhoto_id(fileServiceImpl.addFile(fileUpload.getImage().getBytes()).getId());
		application.setCv_id(fileServiceImpl.addFile(fileUpload.getCv().getBytes()).getId());
		if (applicationService.addApplication(application) != null) {
			try {
				emailSender.sendEmail("Hi, your application added", "Bingo!", application.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public void createNewUser(@Valid @RequestBody User user) throws Exception {
		Optional<User> oUser = userServiceImpl.getUserByUsernameRegistration(user.getUsername());
		if (oUser.isPresent()) {
			throw new IllegalArgumentException("User is already exist");
		}
		userServiceImpl.addUser(user);
	}

}
