package devlight.edu.conference.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Application;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Override
	public Application getApplicationById(int id) {
		return new Application(id, "Name", "Surname", "097263648", new Date(1999, 05, 05), 1, 1, 9.9, "dzioba99@gmail.com", 1, 1, true, null);
	}

	@Override
	public List<Application> getAllApplications() {
		ArrayList<Application> list = new ArrayList<Application>();
		list.add(new Application(1, "Name", "Surname", "097263648", new Date(1999, 05, 05), 1, 1, 9.9, "dzioba99@gmail.com", 1, 1, true, null));
		list.add(new Application(2, "Name", "Surname", "097263648", new Date(1999, 05, 05), 1, 1, 9.9, "dzioba99@gmail.com", 1, 1, true, null));

		return list;
	}

	@Override
	public void addApplication(Application application) {
		System.out.println(application.toString());
	}

	@Override
	public void deleteApplication(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateApplication(int id) {
		// TODO Auto-generated method stub

	}

}
