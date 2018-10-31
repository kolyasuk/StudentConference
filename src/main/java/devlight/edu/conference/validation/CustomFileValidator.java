package devlight.edu.conference.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import devlight.edu.conference.model.FileUpload;

@Component
public class CustomFileValidator implements Validator {

	public static final long MAX_FILE_SIZE_IN_BYTES = 500000;

	@Value("${upload.file.size.max}")
	private String maxSizeMessage;
	@Value("${upload.file.required}")
	private String fileRequiredMessage;

	@Override
	public boolean supports(Class<?> clazz) {
		return FileUpload.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload file = (FileUpload) target;
		MultipartFile mfi = file.getImage();
		MultipartFile mfc = file.getCv();
		if (mfi.isEmpty() || mfc.isEmpty()) {
			errors.rejectValue("file", fileRequiredMessage);
		}
		if (mfi.getSize() > MAX_FILE_SIZE_IN_BYTES || mfc.getSize() > MAX_FILE_SIZE_IN_BYTES) {
			errors.rejectValue("file", maxSizeMessage);
		}

	}

}
