package com.movie.cinemaroom.service;

import java.util.List;

import com.movie.cinemaroom.dto.MovieDto;

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
	 * Find all movies
	 * @return
	 */
	public List<MovieDto> findAll();
	
	/**
	 * Delete movie by id
	 * @param id
	 * @return
	 */
	public boolean delete(String id);
}
