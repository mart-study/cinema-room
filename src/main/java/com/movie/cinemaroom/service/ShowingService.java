package com.movie.cinemaroom.service;

import com.movie.cinemaroom.dto.PagingResultDto;
import com.movie.cinemaroom.dto.ShowingDto;

public interface ShowingService {

	/**
	 * Save new showing
	 * @param showingDto
	 * @return
	 */
	public ShowingDto save(ShowingDto showingDto);
	
	/**
	 * Update existing showing
	 * @param showingDto
	 * @return
	 */
	public ShowingDto update(ShowingDto showingDto);
	
	/**
	 * Find showing by id
	 * @param id
	 * @return
	 */
	public ShowingDto findById(String id);
	
	/**
	 * Get all showings using pagination
	 * @param page
	 * @param size
	 * @return
	 */
	public PagingResultDto findAll(int page, int size);
	
	/**
	 * Delete showing by id
	 * @param id
	 */
	public void delete(String id);
	
}
