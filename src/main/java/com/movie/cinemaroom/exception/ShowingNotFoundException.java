package com.movie.cinemaroom.exception;

public class ShowingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5433995349555055947L;

	public ShowingNotFoundException(String message) {
		super(message);
	}
	
}
