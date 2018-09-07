package devlight.edu.conference.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "NO user")
public class ThereIsNoSuchUserException extends RuntimeException {

}
