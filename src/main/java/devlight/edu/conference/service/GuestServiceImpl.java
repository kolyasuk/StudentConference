package devlight.edu.conference.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.FileRepository;

@Service
public class GuestServiceImpl implements GuestService {

	private final FileRepository fileRepository;
	private final ApplicationRepository applicationRepository;
	private final EmailSender emailSender;

	public GuestServiceImpl(FileRepository fileRepository, ApplicationRepository applicationRepository, EmailSender emailSender) {
		this.fileRepository = fileRepository;
		this.applicationRepository = applicationRepository;
		this.emailSender = emailSender;
	}

	@Override
	public Application createApplication(FileUpload fileUpload, Application application) throws Exception {
		File image = new File(fileUpload.getImage().getBytes());
		File CV = new File(fileUpload.getCv().getBytes());
		application.setPhoto_id(fileRepository.save(image).getId());
		application.setCv_id(fileRepository.save(CV).getId());
		Application savedApplication = applicationRepository.save(application);

		sendMessage(savedApplication);
		return savedApplication;
	}

	private void sendMessage(Application application) throws Exception {
		if (!StringUtils.isEmpty(application.getEmail())) {
			String message = String.format("Hello, %s! \n" + "Your application has been added! Just wait for the evaluation letter.", application.getName());
			emailSender.sendEmail(application.getEmail(), "Application", message);
		}
	}

}
