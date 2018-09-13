package devlight.edu.conference.service;

import java.util.List;

import devlight.edu.conference.model.User;

public interface UserService {

	User getUserById(int id);

	User getUserByLogin(String login);

	List<User> getAllUsers();

	void addUser(User user);

	void deleteUser(int id);

	void updateUser(int id);

}
