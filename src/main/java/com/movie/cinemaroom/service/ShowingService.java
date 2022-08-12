package com.movie.cinemaroom.service;

import java.util.List;

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
	 * Find all showings
	 * @return
	 */
	public List<ShowingDto> findAll();
	
	/**
	 * Delete showing by id
	 * @param id
	 */
	public void delete(String id);
	
}
