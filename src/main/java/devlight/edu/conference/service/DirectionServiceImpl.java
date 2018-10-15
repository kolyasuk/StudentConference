package devlight.edu.conference.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Direction;
import devlight.edu.conference.repository.DirectionRepository;
import javassist.NotFoundException;

@Service
public class DirectionServiceImpl implements DirectionService {

	@Autowired
	DirectionRepository directionRepository;

	@Override
	public Direction addDirection(Direction direction) throws NotFoundException {
		Optional<Direction> oDirection = directionRepository.findByDirectionName(direction.getDirectionName());
		if (!oDirection.isPresent())
			return directionRepository.save(direction);
		else
			throw new NotFoundException("Direction is already found");
	}

	@Override
	public void editDirection(Direction direction) {
		directionRepository.save(direction);
	}

	@Override
	public Direction getDirection(int id) throws NotFoundException {
		Optional<Direction> direction = directionRepository.findById(id);
		if (direction.isPresent())
			return direction.get();
		else
			throw new NotFoundException("Direction is not found");
	}

	@Override
	public List<Direction> getDirectionList() throws NotFoundException {
		List<Direction> directionList = directionRepository.findAll();
		if (directionList != null)
			return directionList;
		else
			throw new NotFoundException("There are no any directions in DataBase");
	}

	@Override
	public void deleteDirection(int id) throws NotFoundException {
		if (getDirection(id) != null)
			directionRepository.deleteById(id);
	}
}
