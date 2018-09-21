package com.learnit.rest.webservices.restfulwebservices.user;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/// oma luokka
import com.learnit.rest.webservices.restfulwebservices.exception.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler{
	
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
		(Exception ex, WebRequest request){
			
		Date d = new Date();
		String s = ex.getMessage();
		String details = "blaaah";
		ExceptionResponse 
		exceptionResponse =
				new ExceptionResponse(d, s, request.getDescription(false));
		
		return new ResponseEntity(
				exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	
		
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException
	(Exception ex, WebRequest request){
		
		Date d = new Date();
		String s = ex.getMessage();
		String details = "blaaah";
		ExceptionResponse 
			exceptionResponse =
				new ExceptionResponse(d, s, request.getDescription(false));
	
		return new ResponseEntity(
			exceptionResponse, HttpStatus.NOT_FOUND);

	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			 HttpHeaders headers,
			HttpStatus status, WebRequest request){
		
		ExceptionResponse 
		exceptionResponse =
			new ExceptionResponse(new Date(), "Validation Failure", ex.getBindingResult().toString());
		
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
}
