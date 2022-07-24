package com.movie.cinemaroom.model;

public class Showing {

	private long showingId;
	private long startTime;
	private int numSeats;
	
	public Showing() {
		
	}

	public Showing(long showingId, long startTime, int numSeats) {
		super();
		this.showingId = showingId;
		this.startTime = startTime;
		this.numSeats = numSeats;
	}

	public long getShowingId() {
		return showingId;
	}

	public void setShowingId(long showingId) {
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
	public String toString() {
		return "Showing [showingId=" + showingId + ", startTime=" + startTime + ", numSeats=" + numSeats + "]";
	}
}
