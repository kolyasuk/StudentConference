package devlight.edu.conference.service;

import java.util.List;

import org.springframework.stereotype.Service;

import devlight.edu.conference.model.Direction;
import javassist.NotFoundException;

@Service
public interface DirectionService {

	Direction addDirection(Direction direction) throws NotFoundException;

	Direction getDirection(int id) throws NotFoundException;

	List<Direction> getDirectionList() throws NotFoundException;

	void deleteDirection(int id) throws NotFoundException;

	void editDirection(Direction direction);

}
