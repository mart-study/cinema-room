package com.movie.cinemaroom.model;

import java.util.Date;

public class Movie {

	private long id;
	private String title;
	private int runTime;
	private float rating;
	private String productionCompany;
	private Date startDate;
	private Date endDate;
	
	public Movie() {
		
	}

	public Movie(long id, String title, int runTime, float rating, String productionCompany, Date startDate,
			Date endDate) {
		super();
		this.id = id;
		this.title = title;
		this.runTime = runTime;
		this.rating = rating;
		this.productionCompany = productionCompany;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", runTime=" + runTime + ", rating=" + rating
				+ ", productionCompany=" + productionCompany + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
}
