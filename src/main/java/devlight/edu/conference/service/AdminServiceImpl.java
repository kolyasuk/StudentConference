package devlight.edu.conference.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
public class AdminServiceImpl implements AdminService {

	private final FileRepository fileRepository;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final DirectionRepository directionRepository;
	private final CuratorRepository curatorRepository;
	private final ApplicationRepository applicationRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final EmailSender emailSender;

	public AdminServiceImpl(FileRepository fileRepository, UserRepository userRepository, RoleRepository roleRepository, DirectionRepository directionRepository, CuratorRepository curatorRepository, ApplicationRepository applicationRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, EmailSender emailSender) {
		this.fileRepository = fileRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.directionRepository = directionRepository;
		this.curatorRepository = curatorRepository;
		this.applicationRepository = applicationRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.emailSender = emailSender;
	}

	@Override
	public File getFile(int id) {
		Optional<File> file = fileRepository.findById(id);
		return file.get();

	}

	@Override
	public User getUser(int id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	@Override
	public User getUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.get();
	}

	@Override
	public List<User> getAllUsers() throws NotFoundException {
		if (userRepository.findAll() != null)
			return userRepository.findAll();
		else
			throw new NotFoundException("There are no users in DataBase");
	}

	@Override
	public void addUser(User user) {
		Optional<User> oUser = userRepository.findByUsername(user.getUsername());
		if (oUser.isPresent()) {
			throw new IllegalArgumentException("User is already exist");
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_JURY"))));
		userRepository.save(user);
	}

	@Override
	public void editUser(User user) throws NotFoundException {
		Optional<User> userFromDB = userRepository.findById(user.getId());
		if (userFromDB.isPresent()) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRoles(userFromDB.get().getRoles());
			userRepository.save(user);
		} else
			throw new NotFoundException("User is not found");
	}

	@Override
	public void deleteUser(int id, String username) throws NotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			if (user.get().getId() != id)
				userRepository.deleteById(id);
			else if (userRepository.findByUsername(username).get().getId() == id)
				throw new IllegalArgumentException("You can't delete your account");
		} else
			throw new NotFoundException("User is not found");
	}

	@Override
	public Direction addDirection(Direction direction) throws NotFoundException {
		Optional<Direction> oDirection = directionRepository.findByDirectionName(direction.getDirectionName());
		if (!oDirection.isPresent())
			return directionRepository.save(direction);
		else
			throw new NotFoundException("Direction is already found");
	}

	@Override
	public void editDirection(Direction direction) {
		directionRepository.save(direction);
	}

	@Override
	public void deleteDirection(int id) throws NotFoundException {
		if (directionRepository.findById(id).isPresent())
			directionRepository.deleteById(id);
		else
			throw new NotFoundException("Direction is not found");
	}

	@Override
	public Curator addCurator(Curator curator) throws NotFoundException {
		Optional<Curator> oCurator = curatorRepository.findByNameAndSurname(curator.getName(), curator.getSurname());
		if (!oCurator.isPresent())
			return curatorRepository.save(curator);
		else
			throw new NotFoundException("Curator is already found");
	}

	@Override
	public void editCurator(Curator curator) {
		curatorRepository.save(curator);
	}

	@Override
	public void deleteCurator(int id) throws NotFoundException {
		if (curatorRepository.findById(id).isPresent())
			curatorRepository.deleteById(id);
		else
			throw new NotFoundException("Curator is not found");
	}

	@Override
	public void deleteApplication(int id) {
		if (applicationRepository.findById(id).isPresent())
			applicationRepository.deleteById(id);
	}

	@Override
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
