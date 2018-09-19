package devlight.edu.conference.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserById(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);

	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);

	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

}
