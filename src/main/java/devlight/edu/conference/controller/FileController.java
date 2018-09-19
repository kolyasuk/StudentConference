package devlight.edu.conference.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import devlight.edu.conference.model.File;
import devlight.edu.conference.service.FileServiceImpl;

@RestController
public class FileController {

	@Autowired
	FileServiceImpl fileServiceImpl;

	@GetMapping(value = "/file/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public File getFileById(@PathVariable("id") int id) {
		return fileServiceImpl.getFile(id);
	}

	@PostMapping(value = "/file")
	public void addFile(@RequestParam("file") MultipartFile uploadedFile) {
		File file = new File();
		try {
			file.setFile(uploadedFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		fileServiceImpl.addFile(file);
	}

	@DeleteMapping(value = "/file/{id}")
	public void deleteFileById(int id) {
		fileServiceImpl.deleteFile(id);
	}

	@PutMapping(value = "/file/{id}")
	public void editFile(@PathVariable("id") int id, @RequestParam("file") MultipartFile newFile) {
		File fileForUpdate = fileServiceImpl.getFile(id);
		try {
			fileForUpdate.setFile(newFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileServiceImpl.editFile(fileForUpdate);
	}

}
