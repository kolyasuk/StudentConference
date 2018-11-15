package devlight.edu.conference.service;

import java.util.List;

import devlight.edu.conference.model.Curator;
import devlight.edu.conference.model.Direction;
import devlight.edu.conference.model.File;
import devlight.edu.conference.model.User;
import javassist.NotFoundException;

public interface AdminService {

	File getFile(int id);

	User getUser(int id);

	User getUser(String username);

	List<User> getAllUsers() throws NotFoundException;

	void addUser(User user);

	void editUser(User user) throws NotFoundException;

	void deleteUser(int id, String username) throws NotFoundException;

	Direction addDirection(Direction direction) throws NotFoundException;

	void editDirection(Direction direction);

	void deleteDirection(int id) throws NotFoundException;

	Curator addCurator(Curator curator) throws NotFoundException;

	void editCurator(Curator curator);

	void deleteCurator(int id) throws NotFoundException;

	void deleteApplication(int id);

	void approveApplication(int id, boolean approveValue) throws Exception;

}
