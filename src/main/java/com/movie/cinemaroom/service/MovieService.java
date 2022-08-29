package com.movie.cinemaroom.service;


import com.movie.cinemaroom.dto.MovieDto;
import com.movie.cinemaroom.dto.PagingResultDto;

public interface MovieService {

	/**
	 * Save movie
	 * @param movieDto
	 * @return
	 */
	public MovieDto save(MovieDto movieDto);
	
	/**
	 * Update movie
	 * @param movieDto
	 * @return
	 */
	public MovieDto update(MovieDto movieDto);
	
	/**
	 * Find movie by id
	 * @param id
	 * @return
	 */
	public MovieDto findById(String id);
	
	/**
	 * Find movie by title (get first result)
	 * @param title
	 * @return
	 */
	public MovieDto findByTitle(String title);
	
	/**
	 * Get all movie using pagination
	 * @param page
	 * @param size
	 * @return
	 */
	public PagingResultDto findAll(int page, int size);
	
	/**
	 * Delete movie by id
	 * @param id
	 * @return
	 */
	public boolean delete(String id);
}
