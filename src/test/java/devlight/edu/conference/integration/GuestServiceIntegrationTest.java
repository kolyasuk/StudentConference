package devlight.edu.conference.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.FileRepository;
import devlight.edu.conference.service.GuestService;
import devlight.edu.conference.validation.CustomFileValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GuestServiceIntegrationTest {

	private final static String SOME_VALUE = "test";

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private GuestService guestService;

	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private ApplicationRepository applicationRepository;

	@Test
	public void createApplication() throws Exception {

		MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, SOME_VALUE.getBytes());
		MockMultipartFile cv = new MockMultipartFile("cv", "cv.docx", CustomFileValidator.CV_FILE_MIME_TYPES, SOME_VALUE.getBytes());

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("name", "testname");
		parameters.add("surname", "testsurname");
		parameters.add("phone", "0696969696");
		parameters.add("birthdate", "1999-05-06");
		parameters.add("email", "example@gmail.com");
		parameters.add("direction_id", "118");
		parameters.add("curator_id", "121");

		MvcResult result = mvc.perform(multipart("/guest/application").file(image).file(cv).params(parameters)).andExpect(status().is2xxSuccessful()).andReturn();

		Application application = objectMapper.readValue(result.getResponse().getContentAsString(), Application.class);
		applicationRepository.deleteById(application.getId());
		fileRepository.deleteById(application.getPhoto_id());
		fileRepository.deleteById(application.getCv_id());
	}

}
