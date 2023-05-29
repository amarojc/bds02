package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseIntegrityViolationException;
import com.devsuperior.bds02.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(
			ObjectNotFoundException ex, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource not found");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(DatabaseIntegrityViolationException.class)
	public ResponseEntity<StandardError> databaseIntegrityViolation(
				DatabaseIntegrityViolationException dex, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Database Integrity violation");
		error.setMessage(dex.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
}
