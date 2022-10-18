package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

	private static final long serialVersionUID = 320421705939365614L;
	
	public UnsupportedMathOperationException(String message) {
		super(message);
	}

}