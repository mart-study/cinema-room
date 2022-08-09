package com.movie.cinemaroom.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.movie.cinemaroom.dto.MovieDto;
import com.movie.cinemaroom.exception.MovieNotFoundException;
import com.movie.cinemaroom.model.Movie;
import com.movie.cinemaroom.repository.MovieRepository;
import com.movie.cinemaroom.service.impl.MovieServiceImpl;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {

	@Spy
	private ModelMapper modelMapper;
	
	@Mock
	private MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	private MovieDto movieDto;
	private Movie movie;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
	void before() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2022-06-29");
		Date endDate = formatter.parse("2022-08-29");
		this.movieDto = new MovieDto("Minions: The Rise of Gru", 87, "SU", 
				"Illumination entertainment", startDate, endDate);
		this.movieDto.setId("48d48865-e1f1-4bc2-9e2f-a32cda343c08");
		
		this.movie = modelMapper.map(this.movieDto, Movie.class);
		Optional<Movie> movieOpt = Optional.of(this.movie);
		when(movieRepository.findById("48d48865-e1f1-4bc2-9e2f-a32cda343c08")).thenReturn(movieOpt);
		when(movieRepository.save(any())).thenReturn(this.movie);
		
		List<Movie> movieList = new ArrayList<>();
		movieList.add(this.movie);
		when(movieRepository.findAll()).thenReturn(movieList);
	}
	
	@Test
	@DisplayName("Test save movie, expected success")
	void saveMovie_success() throws ParseException {
		MovieDto movieDto1 = movieService.save(this.movieDto);
		assertNotNull(movieDto1);
		
		verify(movieRepository, times(1)).save(this.movie);
	}
	
	@Test
	@DisplayName("Test update movie, expected success")
	void updateMovie_success() throws ParseException {
		MovieDto movieDto = movieService.update(this.movieDto);
		Movie movie = modelMapper.map(movieDto, Movie.class);
		movie.setProductionCompany("Illumination Entertainment");
		assertNotEquals(movieDto.getProductionCompany(), movie.getProductionCompany());
		movie = movieRepository.save(movie);
		assertNotNull(movie);
	}
	
	@Test
	@DisplayName("Test update movie, expected failed")
	void updateMovie_failed() throws ParseException {
		MovieDto movieDto = new MovieDto();
		movieDto.setId("invalid_id");
		MovieNotFoundException exception = assertThrows(MovieNotFoundException.class, 
				() -> movieService.update(movieDto), "MovieNotFoundException is expected");
	
		assertEquals("Movie with id: invalid_id is not found", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test delete movie, expected success")
	void deleteMovie_success() throws ParseException {
		movieService.delete("48d48865-e1f1-4bc2-9e2f-a32cda343c08");
	}
	
	@Test
	@DisplayName("Test delete movie, expected failed")
	void deleteMovie_failed() throws ParseException {
		Optional<Movie> movieOpt = Optional.empty();
		when(movieRepository.findById("58d48865-e1f1-4bc2-9e2f-a32cda343c05")).thenReturn(movieOpt);
		
		MovieNotFoundException exception = assertThrows(MovieNotFoundException.class, 
				() -> movieService.delete("58d48865-e1f1-4bc2-9e2f-a32cda343c05"),
				"MovieNotFoundException is expected");
	
		assertEquals("Movie with id: 58d48865-e1f1-4bc2-9e2f-a32cda343c05 is not found", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test find by id, expected success")
	void findById_success() {
		MovieDto movieDto = movieService.findById("48d48865-e1f1-4bc2-9e2f-a32cda343c08");
		assertNotNull(movieDto);
		assertEquals("Minions: The Rise of Gru", movieDto.getTitle());
        verify(movieRepository, times(1)).findById("48d48865-e1f1-4bc2-9e2f-a32cda343c08");
	}
	
	@Test
	@DisplayName("Test find by id, expected failed")
	void findById_failed() {
		MovieNotFoundException exception = assertThrows(MovieNotFoundException.class, 
				() -> movieService.findById("invalid_id"), "MovieNotFoundException is expected");
	
		assertEquals("Movie with id: invalid_id is not found", exception.getMessage());
	}
	
	@Test
	@DisplayName("Test find all movies, expected success")
	void findAllMovies_success() {
		List<MovieDto> listMovie = movieService.findAll();
		assertNotNull(listMovie);
		assertEquals(1, listMovie.size());
		assertNotEquals(0, listMovie.size());
		verify(movieRepository, times(1)).findAll();
	}
	
}
