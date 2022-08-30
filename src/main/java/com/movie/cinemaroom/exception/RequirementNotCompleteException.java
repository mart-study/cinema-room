package com.movie.cinemaroom.exception;

public class RequirementNotCompleteException extends RuntimeException {

	private static final long serialVersionUID = 4688891397497018753L;

	public RequirementNotCompleteException(String message) {
		super(message);
	}
}
