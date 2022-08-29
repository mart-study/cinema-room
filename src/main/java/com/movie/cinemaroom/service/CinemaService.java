package com.movie.cinemaroom.service;

import com.movie.cinemaroom.dto.CinemaDto;
import com.movie.cinemaroom.dto.PagingResultDto;

public interface CinemaService {

	/**
	 * Save new cinema
	 * @param cinemaDto
	 * @return
	 */
	public CinemaDto save(CinemaDto cinemaDto);
	
	/**
	 * Update new cinema
	 * @param cinemaDto
	 * @return
	 */
	public CinemaDto update(CinemaDto cinemaDto);
	
	/**
	 * Find cinema by id
	 * @param id
	 * @return
	 */
	public CinemaDto findById(String id);
	
	/**
	 * Get all cinemas with pagination
	 * @param page
	 * @param size
	 * @return
	 */
	public PagingResultDto findAll(int page, int size);
	
	/**
	 * Delete cinema by id
	 * @param id
	 */
	public void delete(String id);
	
}
