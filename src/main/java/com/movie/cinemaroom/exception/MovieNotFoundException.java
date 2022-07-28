package com.movie.cinemaroom.exception;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6139237829187318191L;

	public MovieNotFoundException(String message) {
		super(message);
	}
}
