package com.movie.cinemaroom.service;

import java.util.List;

import com.movie.cinemaroom.dto.MovieDto;

public interface MovieService {

	public MovieDto save(MovieDto movieDto);
	public MovieDto update(MovieDto movieDto);
	public MovieDto findById(String id);
	public List<MovieDto> findByTitle(String title);
	public List<MovieDto> findAllActiveMovies();
	public boolean deleteMovie(String id);
}
