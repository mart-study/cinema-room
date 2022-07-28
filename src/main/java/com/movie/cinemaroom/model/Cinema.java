package com.movie.cinemaroom.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class Cinema {

	@Id
	private long screenId;
	
	@NotNull
	@Size(min = 25, max = 36)
	private int maxSeats;
	
	@NotNull
	private int sreenSize;
	
	@Reference
	private Set<Showing> showings = new HashSet<>();
	
	private boolean active;
	
	public Cinema() {
		
	}

	public Cinema(int maxSeats, int sreenSize) {
		this.maxSeats = maxSeats;
		this.sreenSize = sreenSize;
		this.active = true;
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
	public int hashCode() {
		return Objects.hash(maxSeats, screenId, sreenSize);
	}

	public Set<Showing> getShowings() {
		return showings;
	}

	public void setShowings(Set<Showing> showings) {
		this.showings = showings;
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
		Cinema other = (Cinema) obj;
		return maxSeats == other.maxSeats && screenId == other.screenId 
				&& sreenSize == other.sreenSize && active == other.active;
	}

	@Override
	public String toString() {
		return "Cinema [screenId=" + screenId + ", maxSeats=" + maxSeats + ", sreenSize=" + sreenSize + ", showings="
				+ showings + ", active=" + active + "]";
	}
}
