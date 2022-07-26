package com.movie.cinemaroom.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

@Validated
public class MovieDto {

	private String id;
	
	@NotBlank(message = "Movie title is empty.")
	@Size(min = 1, max = 100)
	private String title;
	
	@NotNull
	private int runTime;
	
	@NotBlank(message = "Movie rating is empty.")
	private String rating;
	
	@NotBlank(message = "Movie production company is empty.")
	private String productionCompany;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private boolean active;
	
	public MovieDto() {
		
	}

	public MovieDto(String title, int runTime, String rating, String productionCompany, Date startDate, Date endDate) {
		this.title = title;
		this.runTime = runTime;
		this.rating = rating;
		this.productionCompany = productionCompany;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = true;
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
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
		MovieDto other = (MovieDto) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
				&& Objects.equals(productionCompany, other.productionCompany)
				&& Objects.equals(rating, other.rating) && runTime == other.runTime
				&& Objects.equals(startDate, other.startDate) && Objects.equals(title, other.title)
				&& active == other.active;
	}

	@Override
	public String toString() {
		return "MovieDto [id=" + id + ", title=" + title + ", runTime=" + runTime + ", rating=" + rating
				+ ", productionCompany=" + productionCompany + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", active=" + active + "]";
	}
}
