package devlight.edu.conference.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.service.FileServiceImpl;
import devlight.edu.conference.validation.CustomFileValidator;
import javassist.NotFoundException;

@RestController
@RequestMapping("/file/")
public class FileController {

	@Autowired
	FileServiceImpl fileServiceImpl;

	@Autowired
	CustomFileValidator customFileValidator;

	@InitBinder
	public void initBinderFile(WebDataBinder binder) {
		binder.addValidators(customFileValidator);
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public File getFileById(@PathVariable("id") int id) throws NotFoundException {
		return fileServiceImpl.getFileById(id);
	}

	@PostMapping
	public ResponseEntity<File> addFile(@ModelAttribute @Validated FileUpload fileUpload) throws IOException {
		File fileToDb = new File();
		fileToDb.setFileData(fileUpload.getFile().getBytes());
		fileServiceImpl.addFile(fileToDb);
		return new ResponseEntity<>(fileToDb, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "{id}")
	public void deleteFileById(int id) throws NotFoundException {
		if (fileServiceImpl.getFileById(id) == null)
			fileServiceImpl.deleteFile(id);
	}

	@PostMapping(value = "update")
	public ResponseEntity<File> editFile(@ModelAttribute @Validated FileUpload fileUpload) throws NotFoundException {
		File fileForUpdate = fileServiceImpl.getFileById(fileUpload.getId());
		try {
			fileForUpdate.setFileData(fileUpload.getFile().getBytes());
			fileServiceImpl.editFile(fileForUpdate);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(fileForUpdate, HttpStatus.CREATED);

	}

}
