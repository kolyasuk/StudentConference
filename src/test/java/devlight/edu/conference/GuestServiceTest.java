package devlight.edu.conference;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.FileRepository;
import devlight.edu.conference.service.EmailSender;
import devlight.edu.conference.service.GuestServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceTest {

	@Autowired
	GuestServiceImpl guestService;

	@MockBean
	private ApplicationRepository applicationRepository;

	@MockBean
	private EmailSender emailSender;

	@MockBean
	private FileRepository fileRepository;

	@Mock
	private FileUpload fileUpload;

	@Test
	public void createApplication() throws Exception {
		Application application = new Application();
		application.setEmail("some@mail.ru");

		MultipartFile mockMF = new MockMultipartFile("name", "".getBytes());
		Mockito.when(fileUpload.getImage()).thenReturn(mockMF);
		Mockito.when(fileUpload.getCv()).thenReturn(mockMF);
		Mockito.when(fileRepository.save(ArgumentMatchers.any(File.class))).thenReturn(new File(1), new File(2));

		Mockito.when(applicationRepository.save(application)).thenReturn(application);

		guestService.createApplication(fileUpload, application);

		Assert.assertEquals(1, application.getPhoto_id());
		Assert.assertEquals(2, application.getCv_id());

		Mockito.verify(applicationRepository, Mockito.times(1)).save(application);
		Mockito.verify(emailSender, Mockito.times(1)).sendEmail(
				ArgumentMatchers.eq(application.getEmail()),
				ArgumentMatchers.anyString(), 
				ArgumentMatchers.anyString());

	}

}
