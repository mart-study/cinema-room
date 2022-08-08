package com.movie.cinemaroom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.movie.cinemaroom.dto.message.ErrorMessage;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({MovieNotFoundException.class, CinemaNotFoundException.class})
	public ResponseEntity<ErrorMessage> idNotFoundException(RuntimeException exception) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException exception) {
		StringBuilder errorMessage = new StringBuilder();
		exception.getBindingResult().getAllErrors().forEach(error -> {
			String errorFieldMessage = error.getDefaultMessage();
			errorMessage.append(errorFieldMessage + " ");
		});
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), errorMessage.toString());
		return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
	}
	
}
