package devlight.edu.conference.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Curator;
import devlight.edu.conference.repository.CuratorRepository;
import javassist.NotFoundException;

@Service
public class CuratorServiceImpl implements CuratorService {

	@Autowired
	CuratorRepository curatorRepository;

	@Override
	public Curator addCurator(Curator curator) throws NotFoundException {
		Optional<Curator> oCurator = curatorRepository.findByNameAndSurname(curator.getName(), curator.getSurname());
		if (!oCurator.isPresent())
			return curatorRepository.save(curator);
		else
			throw new NotFoundException("Curator is already found");
	}

	@Override
	public void editCurator(Curator curator) {
		curatorRepository.save(curator);
	}

	@Override
	public Curator getCurator(int id) throws NotFoundException {
		Optional<Curator> curator = curatorRepository.findById(id);
		if (curator.isPresent())
			return curator.get();
		else
			throw new NotFoundException("Curator is not found");
	}

	@Override
	public List<Curator> getCuratorList() throws NotFoundException {
		List<Curator> curatorList = curatorRepository.findAll();
		if (curatorList != null)
			return curatorList;
		else
			throw new NotFoundException("There are no any curators in DataBase");
	}

	@Override
	public void deleteCurator(int id) throws NotFoundException {
		if (getCurator(id) != null)
			curatorRepository.deleteById(id);
	}
}
