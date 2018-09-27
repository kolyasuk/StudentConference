package devlight.edu.conference.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.service.FileServiceImpl;
import devlight.edu.conference.validation.CustomFileValidator;
import javassist.NotFoundException;

@RestController
public class FileController {

	@Autowired
	FileServiceImpl fileServiceImpl;

	@Autowired
	CustomFileValidator customFileValidator;

	@InitBinder
	public void initBinderFile(WebDataBinder binder) {
		binder.addValidators(customFileValidator);
	}

	@GetMapping(value = "/file/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public File getFileById(@PathVariable("id") int id) {
		return fileServiceImpl.getFile(id);
	}

	@PostMapping(value = "/file")
	public ResponseEntity<?> addFile(@ModelAttribute @Validated FileUpload fileUpload, BindingResult br) throws NotFoundException, IOException {
		if (br.hasErrors()) {
			throw new NotFoundException(br.getAllErrors().get(0).getCode());
		}
		File file = new File();
		file.setFileData(fileUpload.getFile().getBytes());
		fileServiceImpl.addFile(file);

		return new ResponseEntity<>(file, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/file/{id}")
	public void deleteFileById(int id) {
		if (fileServiceImpl.getFile(id) == null)
			fileServiceImpl.deleteFile(id);
	}

	@PutMapping(value = "/file/{id}")
	public ResponseEntity<?> editFile(@PathVariable("id") int id, @ModelAttribute FileUpload fileUpload, BindingResult bindingResult) {

		customFileValidator.validate(fileUpload, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST).status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().get(0).getCode());
		}

		File file = new File();
		try {
			File fileForUpdate = fileServiceImpl.getFile(id);
			fileForUpdate.setFileData(fileUpload.getFile().getBytes());
			fileServiceImpl.editFile(fileForUpdate);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(file, HttpStatus.CREATED);

	}

}
