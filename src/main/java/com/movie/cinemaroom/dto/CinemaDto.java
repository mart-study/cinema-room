package com.movie.cinemaroom.dto;

import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class CinemaDto {

	private String screenId;
	
	@NotNull
	@Min(25)
	@Max(36)
	private int maxSeats;
	
	@NotNull
	private int sreenSize;
	private Set<ShowingDto> showings;
	private boolean active;
	
	public CinemaDto() {
		
	}

	public CinemaDto(int maxSeats, int sreenSize, Set<ShowingDto> showings) {
		this.maxSeats = maxSeats;
		this.sreenSize = sreenSize;
		this.showings = showings;
		this.active = true;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
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

	public Set<ShowingDto> getShowings() {
		return showings;
	}

	public void setShowings(Set<ShowingDto> showings) {
		this.showings = showings;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, maxSeats, screenId, showings, sreenSize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CinemaDto other = (CinemaDto) obj;
		return active == other.active && maxSeats == other.maxSeats && screenId == other.screenId
				&& Objects.equals(showings, other.showings) && sreenSize == other.sreenSize;
	}

	@Override
	public String toString() {
		return "CinemaDto [screenId=" + screenId + ", maxSeats=" + maxSeats + ", sreenSize=" + sreenSize + ", showings="
				+ showings + ", active=" + active + "]";
	}
}
