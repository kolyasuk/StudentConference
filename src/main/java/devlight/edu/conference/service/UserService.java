package devlight.edu.conference.service;

import java.util.List;

import org.springframework.stereotype.Service;

import devlight.edu.conference.model.User;

@Service
public interface UserService {

	User getUserById(int id);

	List<User> getAllUsers();

	void addUser(User user);

	void deleteUser(int id);

	void updateUser(User user);

}
