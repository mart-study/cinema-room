package com.movie.cinemaroom.model;

public class Cinema {

	private long screenId;
	private int maxSeats;
	private int sreenSize;
	
	public Cinema() {
		
	}

	public Cinema(long screenId, int maxSeats, int sreenSize) {
		this.screenId = screenId;
		this.maxSeats = maxSeats;
		this.sreenSize = sreenSize;
	}

	public long getScreenId() {
		return screenId;
	}

	public void setScreenId(long screenId) {
		this.screenId = screenId;
	}

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	public int getSreenSize() {
		return sreenSize;
	}

	public void setSreenSize(int sreenSize) {
		this.sreenSize = sreenSize;
	}

	@Override
	public String toString() {
		return "Cinema [screenId=" + screenId + ", maxSeats=" + maxSeats + ", sreenSize=" + sreenSize + "]";
	}
}
