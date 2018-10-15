package devlight.edu.conference.service;

import java.util.List;

import devlight.edu.conference.model.Application;
import javassist.NotFoundException;

public interface ApplicationService {

	Application getApplicationById(int id) throws NotFoundException;

	List<Application> getAllApplications() throws NotFoundException;

	Application addApplication(Application application);

	void deleteApplication(int id) throws NotFoundException;

	void editApplication(Application application);

}
