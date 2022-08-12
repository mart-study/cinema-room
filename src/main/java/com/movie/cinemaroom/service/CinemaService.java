package com.movie.cinemaroom.service;

import java.util.List;

import com.movie.cinemaroom.dto.CinemaDto;

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
	 * Find all cinemas
	 * @return
	 */
	public List<CinemaDto> findAll();
	
	/**
	 * Delete cinema by id
	 * @param id
	 */
	public void delete(String id);
	
}
