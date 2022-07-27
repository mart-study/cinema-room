package com.movie.cinemaroom.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.cinemaroom.dto.MovieDto;
import com.movie.cinemaroom.model.Movie;
import com.movie.cinemaroom.repository.MovieRepository;
import com.movie.cinemaroom.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MovieDto save(MovieDto movieDto) {
		Movie movie = modelMapper.map(movieDto, Movie.class);
		movie = movieRepository.save(movie);
		movieDto = modelMapper.map(movie, MovieDto.class);
		return movieDto;
	}
}
