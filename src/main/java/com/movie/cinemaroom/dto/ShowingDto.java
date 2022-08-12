package com.movie.cinemaroom.dto;

import java.util.Objects;

import com.movie.cinemaroom.model.Movie;

public class ShowingDto {

	private String showingId;
	private long startTime;
	private int numSeats;
	private Movie movie;
	private boolean active;
	
	public ShowingDto() {
		
	}
	
	public ShowingDto(String showingId, long startTime, int numSeats, Movie movie, boolean active) {
		this.showingId = showingId;
		this.startTime = startTime;
		this.numSeats = numSeats;
		this.movie = movie;
		this.active = active;
	}

	public String getShowingId() {
		return showingId;
	}
	public void setShowingId(String showingId) {
		this.showingId = showingId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public int getNumSeats() {
		return numSeats;
	}
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, movie, numSeats, showingId, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowingDto other = (ShowingDto) obj;
		return active == other.active && Objects.equals(movie, other.movie) && numSeats == other.numSeats
				&& Objects.equals(showingId, other.showingId) && startTime == other.startTime;
	}

	@Override
	public String toString() {
		return "ShowingDto [showingId=" + showingId + ", startTime=" + startTime + ", numSeats=" + numSeats + ", movie="
				+ movie + ", active=" + active + "]";
	}
}
