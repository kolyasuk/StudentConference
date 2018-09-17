package devlight.edu.conference.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Override
	public Application getApplicationById(int id) {
		return new Application();
	}

	@Override
	public List<Application> getAllApplications() {
		return new ArrayList<Application>();
	}

	@Override
	public void addApplication(Application application) {
		this.applicationRepository.save(application);
	}

	@Override
	public void deleteApplication(int id) {
		this.applicationRepository.deleteById(id);

	}

	@Override
	public void updateApplication(Application application) {
		this.applicationRepository.save(application);
	}

}
