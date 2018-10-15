package devlight.edu.conference.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.repository.ApplicationRepository;
import javassist.NotFoundException;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;

	@Override
	public Application addApplication(Application application) {
		return applicationRepository.save(application);
	}

	@Override
	public void editApplication(Application application) {
		applicationRepository.save(application);
	}

	@Override
	public Application getApplicationById(int id) throws NotFoundException {
		Optional<Application> application = applicationRepository.findById(id);
		if (application.isPresent())
			return application.get();
		else
			throw new NotFoundException("Application is not found");
	}

	@Override
	public List<Application> getAllApplications() throws NotFoundException {
		List<Application> applicationList = applicationRepository.findAll();
		if (applicationList != null)
			return applicationList;
		else
			throw new NotFoundException("There are no any applications in DataBase");
	}

	@Override
	public void deleteApplication(int id) throws NotFoundException {
		if (getApplicationById(id) != null)
			applicationRepository.deleteById(id);
	}
}
