package devlight.edu.conference.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Marks;
import devlight.edu.conference.model.Role;
import devlight.edu.conference.model.User;
import devlight.edu.conference.repository.ApplicationRepository;
import devlight.edu.conference.repository.MarksRepository;
import devlight.edu.conference.repository.RoleRepository;
import devlight.edu.conference.repository.UserRepository;

@Service
public class JuryService {

	@Autowired
	MarksRepository marksRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleRepository roleRepository;

	public void createMark(Marks mark, String username) {
		List<Marks> marksFromDB = marksRepository.findAllByApplicationId(mark.getApplicationId());
		int juryId = userRepository.findByUsername(username).get().getId();
		if (marksFromDB != null && marksFromDB.stream().map(DBMark -> DBMark.getJuryId() == juryId).count() == 0 || marksFromDB == null) {
			marksRepository.save(mark);
			Application application = applicationRepository.getOne(mark.getApplicationId());
			application.setAvarage_mark(marksRepository.getAverageMark(mark.getApplicationId()).get());
			applicationRepository.save(application);
		} else
			throw new IllegalArgumentException("You have already rate this mark");
	}

	public void deleteMark(int markId, String username) {
		int juryId = userRepository.findByUsername(username).get().getId();
		Marks markFromDB = marksRepository.getOne(markId);
		Application application = applicationRepository.getOne(markFromDB.getApplicationId());
		if (markFromDB.getJuryId() == juryId && application.isRevised() == false) {
			marksRepository.delete(markFromDB);
			Optional<Double> mark = marksRepository.getAverageMark(markFromDB.getApplicationId());
			if (mark.isPresent())
				application.setAvarage_mark(mark.get());
			else
				application.setAvarage_mark(0);
			applicationRepository.save(application);
		} else
			throw new IllegalArgumentException("You can't delete this mark");
	}

	public void editJuryAccount(User user, String username) {
		if (user.getUsername().equals(username)) {
			user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_JURY"))));
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		} else
			throw new IllegalArgumentException("You can't edit another user's password");
	}

}
