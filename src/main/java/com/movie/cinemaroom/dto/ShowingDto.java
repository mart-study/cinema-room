package com.movie.cinemaroom.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class ShowingDto {

	private String showingId;
	
	@NotNull
	private long startTime;
	
	@NotNull
	@Min(10)
	@Max(36)
	private int numSeats;
	private MovieDto movie;
	private boolean active;
	
	public ShowingDto() {
		
	}
	
	public ShowingDto(long startTime, int numSeats) {
		this.startTime = startTime;
		this.numSeats = numSeats;
		this.active = true;
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
	public MovieDto getMovie() {
		return movie;
	}

	public void setMovie(MovieDto movie) {
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
