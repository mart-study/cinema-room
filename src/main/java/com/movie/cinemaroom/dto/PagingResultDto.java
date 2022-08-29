package com.movie.cinemaroom.dto;

import java.util.List;
import java.util.Objects;

public class PagingResultDto {

	private List<?> content;
	private int pageElements;
	private int pageNumber;
	private int totalPages;
	private long totalElements;
	
	public PagingResultDto()  {
		
	}

	public PagingResultDto(List<?> content, int pageElements, int pageNumber, int totalPages, long totalElements) {
		super();
		this.content = content;
		this.pageElements = pageElements;
		this.pageNumber = pageNumber;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

	public List<?> getContent() {
		return content;
	}

	public void setContent(List<?> content) {
		this.content = content;
	}

	public int getPageElements() {
		return pageElements;
	}

	public void setPageElements(int pageElements) {
		this.pageElements = pageElements;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, pageElements, pageNumber, totalElements, totalPages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagingResultDto other = (PagingResultDto) obj;
		return Objects.equals(content, other.content) && pageElements == other.pageElements
				&& pageNumber == other.pageNumber && totalElements == other.totalElements
				&& totalPages == other.totalPages;
	}

	@Override
	public String toString() {
		return "PagingResultDto [content=" + content + ", pageElements=" + pageElements + ", pageNumber=" + pageNumber
				+ ", totalPages=" + totalPages + ", totalElements=" + totalElements + "]";
	}
}
