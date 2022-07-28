package com.movie.cinemaroom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.movie.cinemaroom.dto.message.ErrorMessage;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ErrorMessage> movieIdNotFoundException(MovieNotFoundException exception) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
}
