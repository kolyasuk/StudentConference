package devlight.edu.conference.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import devlight.edu.conference.model.Request;
import devlight.edu.conference.service.ThereIsNoSuchUserException;

@RestController
public class MainController {

	@RequestMapping(value = "/request/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Request getRequest(@PathVariable("id") int id) {
		Map<Integer, Request> map = new HashMap<Integer, Request>();
		map.put(1, new Request(1, "Name", "Surname", "097263648", new Date(1999, 05, 05), null, null, 9.9, "dzioba99@gmail.com", 1, 1, true));
		if (map.get(id) == null) {
			throw new ThereIsNoSuchUserException();
		}
		return map.get(id);
	}

	@RequestMapping(value = "/request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<Request> getRequestList() {
		ArrayList<Request> list = new ArrayList<Request>();
		list.add(new Request(1, "Name", "Surname", "097263648", new Date(1999, 05, 05), null, null, 9.9, "dzioba99@gmail.com", 1, 1, true));
		list.add(new Request(2, "Name", "Surname", "097263648", new Date(1999, 05, 05), null, null, 9.9, "dzioba99@gmail.com", 1, 1, true));
		return list;
	}

	@RequestMapping(value = "/createRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void newRequest(@Valid @RequestBody Request request) {
		System.out.println(request.toString());
	}

}
