import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.FileUpload;
import devlight.edu.conference.service.GuestServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceTest {

	@Autowired
	GuestServiceImpl guestService;

	@Test
	public void createApplication() throws Exception {
		Application application = new Application();
		FileUpload fileUpload = new FileUpload();

		guestService.createApplication(fileUpload, application);

		// Assert.

	}

}
