package com.movie.cinemaroom.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class Showing {

	@Id
	private String showingId;
	
	@NotNull
	private long startTime;
	
	@NotNull
	@Size(min = 10, max = 36)
	private int numSeats;
	
	@Reference
	private Movie movie;
	
	private boolean active;
	
	public Showing() {
		
	}

	public Showing(long startTime, int numSeats, Movie movie) {
		this.startTime = startTime;
		this.numSeats = numSeats;
		this.movie = movie;
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

	@Override
	public int hashCode() {
		return Objects.hash(numSeats, showingId, startTime);
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Showing other = (Showing) obj;
		return numSeats == other.numSeats && Objects.equals(showingId, other.showingId) 
				&& startTime == other.startTime && active == other.active;
	}

	@Override
	public String toString() {
		return "Showing [showingId=" + showingId + ", startTime=" + startTime + ", numSeats=" + numSeats + ", movie="
				+ movie + ", active=" + active + "]";
	}
}
