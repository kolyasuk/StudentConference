package devlight.edu.conference.service;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.FileUpload;

public interface GuestService {

	public Application createApplication(FileUpload fileUpload, Application application) throws Exception;

}
