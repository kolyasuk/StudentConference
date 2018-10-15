package devlight.edu.conference.service;

import java.util.List;

import devlight.edu.conference.model.Curator;
import javassist.NotFoundException;

public interface CuratorService {
	Curator addCurator(Curator curator) throws NotFoundException;

	Curator getCurator(int id) throws NotFoundException;

	List<Curator> getCuratorList() throws NotFoundException;

	void deleteCurator(int id) throws NotFoundException;

	void editCurator(Curator curator);

}
