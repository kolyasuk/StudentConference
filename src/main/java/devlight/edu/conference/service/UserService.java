package devlight.edu.conference.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import devlight.edu.conference.model.User;
import javassist.NotFoundException;

@Service
public interface UserService {

	User getUserById(int id) throws NotFoundException;

	Optional<User> getUserByUsername(String username) throws NotFoundException;

	List<User> getAllUsers();

	void addUser(User user);

	void deleteUser(int id);

	void updateUser(User user);

}
