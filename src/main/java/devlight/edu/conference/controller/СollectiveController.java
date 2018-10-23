package devlight.edu.conference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Application;
import devlight.edu.conference.model.Curator;
import devlight.edu.conference.model.Direction;
import devlight.edu.conference.repository.DirectionRepository;
import devlight.edu.conference.service.ApplicationService;
import devlight.edu.conference.service.CuratorServiceImpl;
import devlight.edu.conference.service.DirectionServiceImpl;
import devlight.edu.conference.service.UserServiceImpl;
import devlight.edu.conference.utils.EmailSender;
import javassist.NotFoundException;

@RestController
@RequestMapping("/collective/")
public class СollectiveController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	DirectionRepository directionRepository;

	@Autowired
	CuratorServiceImpl curatorServiceImpl;

	@Autowired
	DirectionServiceImpl directionServiceImpl;

	@Autowired
	EmailSender emailSender;

	@GetMapping("application/{id}")
	public Application getApplication(@PathVariable("id") int id) throws NotFoundException {
		return applicationService.getApplicationById(id);
	}

	@GetMapping("application")
	public List<Application> getApplicationList() throws NotFoundException {
		return applicationService.getAllApplications();
	}

	@GetMapping("curator/{id}")
	public Curator getCurator(@PathVariable("id") int id) throws NotFoundException {
		return curatorServiceImpl.getCurator(id);
	}

	@GetMapping("curator")
	public List<Curator> getCuratorList() throws NotFoundException {
		return curatorServiceImpl.getCuratorList();
	}

	@GetMapping("direction/{id}")
	public Direction getDirection(@PathVariable("id") int id) throws NotFoundException {
		return directionServiceImpl.getDirection(id);
	}

	@GetMapping("direction")
	public List<Direction> getDirectionList() throws NotFoundException {
		return directionServiceImpl.getDirectionList();
	}

}
