package devlight.edu.conference.service;

import java.util.List;

import devlight.edu.conference.model.Application;

public interface ApplicationService {

	Application getApplicationById(int id);

	List<Application> getAllApplications();

	void addApplication(Application application);

	void deleteApplication(int id);

	void updateApplication(Application application);

}
