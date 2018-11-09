package devlight.edu.conference.service;

import devlight.edu.conference.model.Marks;
import devlight.edu.conference.model.User;

public interface JuryService {

	public Marks createMark(Marks mark, String username);

	public void deleteMark(int markId, String username);

	public void editJuryAccount(User user, String username);

}
