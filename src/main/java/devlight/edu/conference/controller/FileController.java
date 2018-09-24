package devlight.edu.conference.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.service.FileServiceImpl;
import devlight.edu.conference.validation.CustomFileValidator;

@RestController
public class FileController {

	@Autowired
	FileServiceImpl fileServiceImpl;

	@Autowired
	CustomFileValidator customFileValidator;

	@GetMapping(value = "/file/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public File getFileById(@PathVariable("id") int id) {
		return fileServiceImpl.getFile(id);
	}

	@PostMapping(value = "/file")
	public void addFile(@ModelAttribute FileUpload fileUpload, BindingResult bindingResult) {

		customFileValidator.validate(fileUpload, bindingResult);
		if (bindingResult.hasErrors()) {
			return;
		}

		try {
			File file = new File();
			file.setFileData(fileUpload.getFile().getBytes());
			fileServiceImpl.addFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@DeleteMapping(value = "/file/{id}")
	public void deleteFileById(int id) {
		fileServiceImpl.deleteFile(id);
	}

	@PutMapping(value = "/file/{id}")
	public void editFile(@PathVariable("id") int id, @RequestParam("file") MultipartFile newFile) {

		try {
			File fileForUpdate = fileServiceImpl.getFile(id);
			fileForUpdate.setFileData(newFile.getBytes());
			fileServiceImpl.editFile(fileForUpdate);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
