package devlight.edu.conference.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Role;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.RoleRepository;
import devlight.edu.conference.repository.UserRepository;
import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User getUserById(int id) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent())
			return user.get();
		else
			throw new NotFoundException("User is not found");
	}

	@Override
	public User getUserByUsername(String username) throws NotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent())
			return user.get();
		else
			throw new NotFoundException("User is not found");
	}

	@Override
	public Optional<User> getUserByUsernameRegistration(String username) {
		return userRepository.findByUsername(username);
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
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_JURY"))));
		userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) throws NotFoundException {
		if (getUserById(id) != null)
			userRepository.deleteById(id);
	}

	@Override
	public void editUser(User user, boolean saveOldRoles) throws NotFoundException {
		User userFromDB = getUserByUsername(user.getUsername());
		if (userFromDB != null) {
			if (saveOldRoles == true)
				user.setRoles(userFromDB.getRoles());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setId(userFromDB.getId());
			userRepository.save(user);
		}
	}

}
