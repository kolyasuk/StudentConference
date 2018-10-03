package devlight.edu.conference.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileUpload {
	int id;
	private MultipartFile file;

}
