package devlight.edu.conference.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;

@Component
public class CustomFileValidator implements Validator {

	public static final long MAX_FILE_SIZE_IN_BYTES = 500000;

	@Override
	public boolean supports(Class<?> clazz) {
		return File.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload fu = (FileUpload) target;
		MultipartFile mfile = fu.getFile();
		if (mfile.isEmpty()) {
			errors.rejectValue("file", "upload.file.required");
		}
		if (mfile.getSize() > MAX_FILE_SIZE_IN_BYTES) {
			errors.rejectValue("file", "upload.file.size.max");
		}

	}

}
