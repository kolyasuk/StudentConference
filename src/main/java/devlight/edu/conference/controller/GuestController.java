package devlight.edu.conference.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.service.FileServiceImpl;
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

	@InitBinder("file")
	public void initBinderFile(WebDataBinder binder) {
		binder.addValidators(customFileValidator);
	}

	@PostMapping("application")
	public void newApplication(@RequestBody @Valid Application application) throws NotFoundException {
		if (applicationService.addApplication(application) != null) {
			try {
				emailSender.sendEmail("Hi, your application added", "Bingo!", application.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@PostMapping("file")
	public ResponseEntity<File> addFile(@ModelAttribute @Validated FileUpload fileUpload) throws IOException {
		File fileToDb = new File();
		fileToDb.setFileData(fileUpload.getFile().getBytes());
		fileServiceImpl.addFile(fileToDb);
		return new ResponseEntity<>(fileToDb, HttpStatus.CREATED);
	}

}
