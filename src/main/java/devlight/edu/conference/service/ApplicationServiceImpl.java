package devlight.edu.conference.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;

	@Override
	public Application getApplicationById(int id) {
		Application ap = applicationRepository.getOne(id);
		System.out.println(ap);
		return ap;
	}

	@Override
	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

	@Override
	public Application addApplication(Application application) {
		return applicationRepository.save(application);
	}

	@Override
	public void deleteApplication(int id) {
		applicationRepository.deleteById(id);

	}

	@Override
	public void updateApplication(Application application) {
		applicationRepository.save(application);
	}

}
