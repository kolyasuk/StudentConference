package devlight.edu.conference.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import devlight.edu.conference.model.User;
import javassist.NotFoundException;

@Service
public interface UserService {

	User getUserById(int id) throws NotFoundException;

	User getUserByUsername(String username) throws NotFoundException;

	Optional<User> getUserByUsernameRegistration(String username);

	List<User> getAllUsers() throws NotFoundException;

	void addUser(User user);

	void deleteUser(int id) throws NotFoundException;

	void editUser(User user) throws NotFoundException;

}
