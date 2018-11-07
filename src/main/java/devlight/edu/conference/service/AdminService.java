package devlight.edu.conference.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Curator;
import devlight.edu.conference.model.Direction;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.Role;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.CuratorRepository;
import devlight.edu.conference.repository.DirectionRepository;
import devlight.edu.conference.repository.FileRepository;
import devlight.edu.conference.repository.RoleRepository;
import devlight.edu.conference.repository.UserRepository;
import javassist.NotFoundException;

@Service
public class AdminService {
	@Autowired
	FileRepository fileRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	DirectionRepository directionRepository;
	@Autowired
	CuratorRepository curatorRepository;
	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	EmailSender emailSender;

	public File getFile(int id) {
		Optional<File> file = fileRepository.findById(id);
		return file.get();

	}

	public User getUser(int id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	public User getUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.get();
	}

	public List<User> getAllUsers() throws NotFoundException {
		if (userRepository.findAll() != null)
			return userRepository.findAll();
		else
			throw new NotFoundException("There are no users in DataBase");
	}

	public void addUser(User user) {
		Optional<User> oUser = userRepository.findByUsername(user.getUsername());
		if (oUser.isPresent()) {
			throw new IllegalArgumentException("User is already exist");
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (user.getRoles().isEmpty())
			user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_JURY"))));
		userRepository.save(user);
	}

	public void editUser(User user) {
		Optional<User> userFromDB = userRepository.findById(user.getId());
		if (userFromDB.isPresent()) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRoles(userFromDB.get().getRoles());
			userRepository.save(user);
		}
	}

	public void deleteUser(int id, String username) {
		if (userRepository.findByUsername(username).get().getId() != id)
			userRepository.deleteById(id);
		else
			throw new IllegalArgumentException("You can't delete your account");
	}

	public Direction addDirection(Direction direction) throws NotFoundException {
		Optional<Direction> oDirection = directionRepository.findByDirectionName(direction.getDirectionName());
		if (!oDirection.isPresent())
			return directionRepository.save(direction);
		else
			throw new NotFoundException("Direction is already found");
	}

	public void editDirection(Direction direction) {
		directionRepository.save(direction);
	}

	public void deleteDirection(int id) throws NotFoundException {
		if (directionRepository.findById(id).isPresent())
			directionRepository.deleteById(id);
		else
			throw new NotFoundException("Direction is not found");
	}

	public Curator addCurator(Curator curator) throws NotFoundException {
		Optional<Curator> oCurator = curatorRepository.findByNameAndSurname(curator.getName(), curator.getSurname());
		if (!oCurator.isPresent())
			return curatorRepository.save(curator);
		else
			throw new NotFoundException("Curator is already found");
	}

	public void editCurator(Curator curator) {
		curatorRepository.save(curator);
	}

	public void deleteCurator(int id) throws NotFoundException {
		if (curatorRepository.findById(id).isPresent())
			curatorRepository.deleteById(id);
		else
			throw new NotFoundException("Curator is not found");
	}

	public void deleteApplication(int id) {
		if (applicationRepository.findById(id).isPresent())
			applicationRepository.deleteById(id);
	}

	public void approveApplication(int id, boolean approveValue) throws Exception {
		Application application = applicationRepository.findById(id).get();
		if (application.isRevised() == true)
			throw new IllegalArgumentException("This application has already revised!");
		else if (application.getAvarage_mark() < 7)
			throw new IllegalArgumentException("This application has bad rating!");
		else {
			application.setApproved(approveValue);
			application.setRevised(true);
			applicationRepository.save(application);
			sendMessage(application);
		}
	}

	private void sendMessage(Application application) throws Exception {
		if (!StringUtils.isEmpty(application.getEmail())) {
			String message = String.format("Hello, %s! \n" + "Your application is %sapproved!", application.getName(), (application.isApproved() ? "" : "not "));
			emailSender.sendEmail(application.getEmail(), "Application", message);
		}
	}

}
