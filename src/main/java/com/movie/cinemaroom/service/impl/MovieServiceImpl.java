package com.movie.cinemaroom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.cinemaroom.dto.MovieDto;
import com.movie.cinemaroom.exception.MovieNotFoundException;
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

	@Override
	public MovieDto update(MovieDto movieDto) {
		Optional<Movie> movieOpt = movieRepository.findById(movieDto.getId());
		if (movieOpt.isEmpty()) {
			throw new MovieNotFoundException("Movie with id: " + movieDto.getId() + " is not found");
		}
		
		Movie movie = movieOpt.get();
		movie.setTitle(movieDto.getTitle());
		movie.setRunTime(movieDto.getRunTime());
		movie.setRating(movieDto.getRating());
		movie.setProductionCompany(movieDto.getProductionCompany());
		movie.setStartDate(movieDto.getStartDate());
		movie.setEndDate(movieDto.getEndDate());
		
		movie = movieRepository.save(movie);
		movieDto = modelMapper.map(movie, MovieDto.class);
		return movieDto;
	}

	@Override
	public boolean deleteMovie(String id) {
		Optional<Movie> movieOpt = movieRepository.findById(id);
		if (movieOpt.isEmpty()) {
			throw new MovieNotFoundException("Movie with id: " + id + " is not found");
		}
		
		Movie movie = movieOpt.get();
		movie.setActive(false);
		movieRepository.save(movie);
		return true;
	}

	@Override
	public MovieDto findById(String id) {
		Optional<Movie> movieOpt = movieRepository.findById(id);
		if (movieOpt.isEmpty()) {
			throw new MovieNotFoundException("Movie with id: " + id + " is not found");
		}
		
		MovieDto movieDto = modelMapper.map(movieOpt.get(), MovieDto.class);
		return movieDto;
	}

	@Override
	public List<MovieDto> findByTitle(String title) {
		List<Movie> movieList = movieRepository.findByTitleLikeIgnoreCase("%" + title + "%");
		List<MovieDto> movieDtoList = new ArrayList<>();
		if (movieList != null && !movieList.isEmpty()) {
			movieList.forEach(movie -> {
				MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
				movieDtoList.add(movieDto);
			});
		}
		return movieDtoList;
	}

	@Override
	public List<MovieDto> findAllActiveMovies() {
		List<Movie> movieList = movieRepository.findByActiveTrue();
		List<MovieDto> movieDtoList = new ArrayList<>();
		if (movieList != null && !movieList.isEmpty()) {
			movieList.forEach(movie -> {
				MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
				movieDtoList.add(movieDto);
			});
		}
		return movieDtoList;
	}
	
}
