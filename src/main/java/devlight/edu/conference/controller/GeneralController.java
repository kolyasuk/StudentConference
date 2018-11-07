package devlight.edu.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javassist.NotFoundException;

@RestControllerAdvice
public class GeneralController {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> validationErrorHandler(BindException ex) {
		return new ResponseEntity<>(ex.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> NotFoundHandler(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
