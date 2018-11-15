package devlight.edu.conference;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Marks;
import devlight.edu.conference.model.Roles;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.MarksRepository;
import devlight.edu.conference.repository.UserRepository;
import devlight.edu.conference.service.JuryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JuryServiceTest {

	@Autowired
	JuryService juryService;

	@MockBean
	MarksRepository marksRepository;

	@MockBean
	UserRepository userRepository;

	@MockBean
	ApplicationRepository applicationRepository;

	@Test
	public void createMark() {
		String username = "uname";
		Marks mark = new Marks();
		mark.setApplicationId(1);
		mark.setMark(10);
		mark.setJuryId(228);

		Mockito.doReturn(Arrays.asList(new Marks(7, 123, 1, 10))).when(marksRepository).findAllByApplicationId(mark.getApplicationId());
		Mockito.doReturn(Optional.of(new User(228, username, "pass", null))).when(userRepository).findByUsername(username);
		Mockito.doReturn(new Application()).when(applicationRepository).getOne(mark.getApplicationId());
		Mockito.doReturn(Optional.of(10.0)).when(marksRepository).getAverageMark(mark.getApplicationId());

		juryService.createMark(mark, username);

		Mockito.verify(marksRepository, times(1)).save(mark);

		Mockito.verify(applicationRepository, times(1)).getOne(mark.getApplicationId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMarkFailTest() {
		String username = "uname";
		Marks mark = new Marks();
		mark.setApplicationId(1);
		mark.setMark(10);
		mark.setJuryId(228);

		Mockito.doReturn(Arrays.asList(new Marks(7, mark.getJuryId(), 1, 10))).when(marksRepository).findAllByApplicationId(mark.getApplicationId());
		Mockito.doReturn(Optional.of(new User(228, username, "pass", null))).when(userRepository).findByUsername(username);
		juryService.createMark(mark, username);

	}

	@Test
	public void deleteMark() {
		int markId = 1;

		User jury = new User();
		jury.setId(5);

		Marks mark = new Marks();
		mark.setApplicationId(3);
		mark.setJuryId(jury.getId());

		Application application = new Application();

		Mockito.doReturn(Optional.of(jury)).when(userRepository).findByUsername(ArgumentMatchers.anyString());
		Mockito.doReturn(mark).when(marksRepository).getOne(markId);
		Mockito.doReturn(application).when(applicationRepository).getOne(mark.getApplicationId());

		juryService.deleteMark(markId, ArgumentMatchers.anyString());

		Mockito.verify(marksRepository, Mockito.times(1)).delete(mark);
		Mockito.verify(applicationRepository, Mockito.times(1)).save(application);

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteMarkFailTestByWrongID() {
		int markId = 1;

		User jury = new User();
		jury.setId(5);

		Marks mark = new Marks();
		mark.setApplicationId(3);
		mark.setJuryId(228);

		Mockito.doReturn(Optional.of(jury)).when(userRepository).findByUsername(ArgumentMatchers.anyString());
		Mockito.doReturn(mark).when(marksRepository).getOne(markId);

		juryService.deleteMark(markId, ArgumentMatchers.anyString());

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteMarkFailTestByApplicationRevised() {
		int markId = 1;

		User jury = new User();
		jury.setId(5);

		Marks mark = new Marks();
		mark.setApplicationId(3);
		mark.setJuryId(jury.getId());

		Application application = new Application();
		application.setRevised(true);

		Mockito.doReturn(Optional.of(jury)).when(userRepository).findByUsername(ArgumentMatchers.anyString());
		Mockito.doReturn(mark).when(marksRepository).getOne(markId);
		Mockito.doReturn(application).when(applicationRepository).getOne(mark.getApplicationId());

		juryService.deleteMark(markId, ArgumentMatchers.anyString());
	}

	@Test
	public void editJuryAccount() {
		String username = "testusername";
		String password = "testpass";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		juryService.editJuryAccount(user, username);

		Assert.assertEquals(Roles.ROLE_JURY.toString(), user.getRoles().iterator().next().getRole());
		Assert.assertNotEquals(password, user.getPassword());
		Mockito.verify(userRepository, Mockito.times(1)).save(user);

	}

	@Test(expected = IllegalArgumentException.class)
	public void editJuryAccountFailTest() {
		String username = "testusername";
		User user = new User();
		user.setUsername("anotherusername");

		juryService.editJuryAccount(user, username);
	}

}
