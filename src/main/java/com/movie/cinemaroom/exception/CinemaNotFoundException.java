package com.movie.cinemaroom.exception;

public class CinemaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7970592590633656560L;

	public CinemaNotFoundException(String message) {
		super(message);
	}
}
