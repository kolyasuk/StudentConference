package devlight.edu.conference;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Role;
import devlight.edu.conference.model.Roles;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.UserRepository;
import devlight.edu.conference.service.AdminService;
import devlight.edu.conference.service.EmailSender;
import javassist.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

	@Autowired
	AdminService adminService;

	@MockBean
	UserRepository userRepository;

	@MockBean
	ApplicationRepository applicationRepository;

	@MockBean
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@MockBean
	private EmailSender emailSender;

	@Test
	public void addUser() {
		String password = "testpass";
		User user = new User();
		user.setPassword(password);

		adminService.addUser(user);

		Assert.assertEquals(Roles.ROLE_JURY.toString(), user.getRoles().iterator().next().getRole());

		Mockito.verify(bCryptPasswordEncoder, Mockito.times(1)).encode(password);

		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addUserFailTest() {
		User user = new User();
		user.setUsername("Test");

		Mockito.doReturn(Optional.of(new User())).when(userRepository).findByUsername(user.getUsername());

		adminService.addUser(user);
	}

	@Test
	public void editUser() throws NotFoundException {
		String password = "testpass";
		User user = new User();
		user.setId(6);
		user.setUsername("uname");
		user.setPassword(password);

		Mockito.doReturn(Optional.of(new User(6, null, null, new HashSet<Role>(Arrays.asList(new Role(3, Roles.ROLE_JURY.toString())))))).when(userRepository).findById(user.getId());

		adminService.editUser(user);

		Assert.assertEquals(Roles.ROLE_JURY.toString(), user.getRoles().iterator().next().getRole());
		Mockito.verify(bCryptPasswordEncoder, Mockito.times(1)).encode(password);
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}

	@Test(expected = NotFoundException.class)
	public void editUserFailTest() throws NotFoundException {
		String password = "testpass";
		User user = new User();
		user.setId(6);
		user.setUsername("uname");
		user.setPassword(password);

		Mockito.doReturn(Optional.empty()).when(userRepository).findById(user.getId());

		adminService.editUser(user);
	}

	@Test
	public void deleteUser() throws NotFoundException {
		int id = 6;
		String username = "uname";

		Mockito.doReturn(Optional.of(new User(1, username, null, null))).when(userRepository).findByUsername(username);

		adminService.deleteUser(id, username);

		Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteUserFailTestBySameId() throws NotFoundException {
		int id = 6;
		String username = "uname";

		Mockito.doReturn(Optional.of(new User(id, username, null, null))).when(userRepository).findByUsername(username);

		adminService.deleteUser(id, username);
	}

	@Test(expected = NotFoundException.class)
	public void deleteUserFailTestByNotFound() throws NotFoundException {
		int id = 6;
		String username = "uname";

		Mockito.doReturn(Optional.empty()).when(userRepository).findByUsername(username);

		adminService.deleteUser(id, username);
	}

	@Test
	public void approveApplication() throws Exception {
		Application application = new Application();
		application.setAvarage_mark(10);
		application.setEmail("some@mail.ru");

		Mockito.doReturn(Optional.of(application)).when(applicationRepository).findById(ArgumentMatchers.anyInt());

		adminService.approveApplication(1, true);

		Assert.assertTrue(application.isApproved());
		Assert.assertTrue(application.isRevised());

		Mockito.verify(applicationRepository, Mockito.times(1)).save(application);
		Mockito.verify(emailSender, Mockito.times(1)).sendEmail(ArgumentMatchers.eq(application.getEmail()), ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void approveApplicationFailTestByRevised() throws Exception {
		Application application = new Application();
		application.setRevised(true);

		Mockito.doReturn(Optional.of(application)).when(applicationRepository).findById(ArgumentMatchers.anyInt());
		adminService.approveApplication(1, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void approveApplicationFailTestByAvarageMark() throws Exception {
		Application application = new Application();
		application.setAvarage_mark(5);

		Mockito.doReturn(Optional.of(application)).when(applicationRepository).findById(ArgumentMatchers.anyInt());
		adminService.approveApplication(1, true);

	}

}
