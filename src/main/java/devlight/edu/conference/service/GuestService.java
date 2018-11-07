package devlight.edu.conference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.FileRepository;

@Service
public class GuestService {

	@Autowired
	FileRepository fileRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	EmailSender emailSender;

	public void createApplication(FileUpload fileUpload, Application application) throws Exception {
		File image = new File(fileUpload.getImage().getBytes());
		application.setPhoto_id(fileRepository.save(image).getId());
		File CV = new File(fileUpload.getCv().getBytes());
		application.setCv_id(fileRepository.save(CV).getId());
		Application savedApplication = applicationRepository.save(application);
		if (savedApplication != null) {
			sendMessage(savedApplication);
		}
	}

	private void sendMessage(Application application) throws Exception {
		if (!StringUtils.isEmpty(application.getEmail())) {
			String message = String.format("Hello, %s! \n" + "Your application has been added! Just wait for the evaluation letter.", application.getName());
			emailSender.sendEmail(application.getEmail(), "Application", message);
		}
	}

}
