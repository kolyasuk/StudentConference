package devlight.edu.conference.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.CuratorRepository;
import devlight.edu.conference.repository.DirectionRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	CuratorRepository curatorRepository;
	@Autowired
	DirectionRepository directionRepository;

	@Override
	public Application getApplicationById(int id) {
		return applicationRepository.getOne(id);
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
