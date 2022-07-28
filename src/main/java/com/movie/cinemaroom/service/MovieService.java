package com.movie.cinemaroom.service;

import com.movie.cinemaroom.dto.MovieDto;

public interface MovieService {

	public MovieDto save(MovieDto movieDto);
	public MovieDto update(MovieDto movieDto);
	public boolean deleteMovie(String id);
}
