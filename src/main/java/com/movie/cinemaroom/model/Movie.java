package com.movie.cinemaroom.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
public class Movie {

	@Id
	private String id;
	
	@NotNull
	@Indexed
	private String title;
	
	@NotNull
	private int runTime;
	
	@NotNull
	private float rating;
	
	@NotNull
	private String productionCompany;
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
	public Movie() {
		
	}

	public Movie(String title, int runTime, float rating, String productionCompany, Date startDate,
			Date endDate) {
		this.title = title;
		this.runTime = runTime;
		this.rating = rating;
		this.productionCompany = productionCompany;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getProductionCompany() {
		return productionCompany;
	}

	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, id, productionCompany, rating, runTime, startDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
				&& Objects.equals(productionCompany, other.productionCompany)
				&& Float.floatToIntBits(rating) == Float.floatToIntBits(other.rating) && runTime == other.runTime
				&& Objects.equals(startDate, other.startDate) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", runTime=" + runTime + ", rating=" + rating
				+ ", productionCompany=" + productionCompany + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
}
