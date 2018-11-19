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
	public static final String IMAGE_FILE_MIME_TYPES = "image/gif, image/jpeg, image/pjpeg, image/png";
	public static final String CV_FILE_MIME_TYPES = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

	@Value("{upload.file.size.max}")
	private String maxSizeMessage;
	@Value("{upload.file.required}")
	private String fileRequiredMessage;
	@Value("{upload.file.format}")
	private String fileFormatMessage;

	@Override
	public boolean supports(Class<?> clazz) {
		return FileUpload.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload file = (FileUpload) target;
		MultipartFile mfimg = file.getImage();
		MultipartFile mfcv = file.getCv();
		if (mfimg.isEmpty() || mfcv.isEmpty()) {
			errors.rejectValue("file", fileRequiredMessage);
		}
		if (mfimg.getSize() > MAX_FILE_SIZE_IN_BYTES || mfcv.getSize() > MAX_FILE_SIZE_IN_BYTES) {
			errors.rejectValue("file", maxSizeMessage);

		}
		if (!IMAGE_FILE_MIME_TYPES.contains(mfimg.getContentType()) || !mfcv.getContentType().equals(CV_FILE_MIME_TYPES)) {
			errors.rejectValue("file", fileFormatMessage);
		}

	}

}
