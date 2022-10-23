package br.com.erudio.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.exceptions.ExceptionResponse;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*@ExceptionHandler(UnsupportedMathOperationException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception exception, WebRequest request) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
	}*/
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception exception, WebRequest request) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception exception, WebRequest request) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
	
}